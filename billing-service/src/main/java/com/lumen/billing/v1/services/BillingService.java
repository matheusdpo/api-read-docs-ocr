package com.lumen.billing.v1.services;

import com.lumen.billing.v1.entities.InvoiceEntity;
import com.lumen.billing.v1.entities.UserEntity;
import com.lumen.billing.v1.repositories.InvoiceEntityRepository;
import com.lumen.billing.v1.repositories.PaymentsRepository;
import com.lumen.billing.v1.repositories.RequestLogsRepository;
import com.lumen.billing.v1.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BillingService {
    private static final int PAYMENT_GRACE_PERIOD_DAYS = 30;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PaymentsRepository paymentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestLogsRepository requestLogsRepository;

    @Autowired
    private InvoiceEntityRepository invoiceEntityRepository;

    public void checkUsersWithOverduePayment() {
        LocalDate cutoffDate = LocalDate.now().minusDays(PAYMENT_GRACE_PERIOD_DAYS);

        //reactivate users who have paid
        List<UserEntity> usersToReactivate = userEntityRepository.findByActiveFalseAndLastPaymentDateAfter(cutoffDate);
        usersToReactivate.forEach(user -> {
            user.setActive(true);
        });
        userEntityRepository.saveAll(usersToReactivate);

        //dieactivate users with overdue payments
        List<UserEntity> usersToDeactivate = userEntityRepository.findByLastPaymentDateBeforeOrLastPaymentDateIsNull(cutoffDate);
        usersToDeactivate.forEach(user -> {
            user.setActive(false);
            user.setLastPaymentDate(null);
        });
        userEntityRepository.saveAll(usersToDeactivate);
    }

    public void generateMonthlyInvoices() {
        LocalDate invoiceDate = LocalDate.now();
        LocalDate dueDate = invoiceDate.plusDays(7); // 10 dias para pagar

        List<UserEntity> activeUsers = userEntityRepository.findAllByActiveTrue();

        activeUsers.forEach(user -> {
            // Calcula uso da API no mês anterior
            LocalDate startDate = invoiceDate.minusMonths(1).withDayOfMonth(1);
            LocalDate endDate = invoiceDate.minusMonths(1).withDayOfMonth(invoiceDate.minusMonths(1).lengthOfMonth());

            int totalRequests = requestLogsRepository.countByUserIdAndRequestDateBetween(user.getId(), startDate.toString(), endDate.toString());

            BigDecimal totalAmount = calculateUsageAmount(totalRequests);

            InvoiceEntity invoice = new InvoiceEntity();
            invoice.setUser(user);
            invoice.setIssueDate(invoiceDate);
            invoice.setDueDate(dueDate);
            invoice.setAmount(totalAmount);
            invoice.setPaid(false);
            invoice.setLate(false);

            invoiceEntityRepository.save(invoice);

            emailService.sendInvoice(user, invoice);
        });
    }

    public void processOverduePayments() {
        LocalDate today = LocalDate.now();

        // Busca faturas não pagas com vencimento anterior a hoje
        List<InvoiceEntity> overdueInvoices = invoiceEntityRepository.findByIsPaidFalseAndDueDateBefore(today);

        overdueInvoices.forEach(invoice -> {
            if (!invoice.isLate()) {
                // Primeira vez que identificamos o atraso
                invoice.setLate(true);
                BigDecimal overdueAmount = calculateOverdueAmount(invoice);
                invoice.setAmount(overdueAmount);
                invoiceEntityRepository.save(invoice);

                emailService.sendPaymentReminder(invoice.getUser(), invoice);
            } else {
                // Já está em atraso, verifica se precisa enviar novo lembrete
                if (ChronoUnit.DAYS.between(invoice.getDueDate(), today) % 7 == 0) {
                    emailService.sendPaymentReminder(invoice.getUser(), invoice);
                }
            }

            // Desativa usuários com mais de 30 dias de atraso
            if (ChronoUnit.DAYS.between(invoice.getDueDate(), today) > 30) {
                invoice.getUser().setActive(false);
                userEntityRepository.save(invoice.getUser());
            }
        });
    }

    private BigDecimal calculateUsageAmount(int totalRequests) {
        // Calcula o valor baseado no uso da API
        // Exemplo: R$0.10 por requisição
        BigDecimal pricePerRequest = BigDecimal.valueOf(0.10);

        return pricePerRequest.multiply(BigDecimal.valueOf(totalRequests));
    }

    private BigDecimal calculateOverdueAmount(InvoiceEntity invoice) {
        long daysLate = ChronoUnit.DAYS.between(invoice.getDueDate(), LocalDate.now());

        // Multa de 2% após 5 dias
        BigDecimal fine = daysLate > 5 ?
                invoice.getAmount().multiply(BigDecimal.valueOf(0.02)) :
                BigDecimal.ZERO;

        // Juros de 1% ao dia (limitado a 30 dias)
        BigDecimal interest = invoice.getAmount().multiply(
                BigDecimal.valueOf(0.01 * Math.min(daysLate, 30)));

        return invoice.getAmount().add(fine).add(interest);
    }
}