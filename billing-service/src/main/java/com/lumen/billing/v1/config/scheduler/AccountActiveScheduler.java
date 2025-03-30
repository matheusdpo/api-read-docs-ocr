package com.lumen.billing.v1.config.scheduler;

import com.lumen.billing.v1.services.BillingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AccountActiveScheduler {
    private static final String DEACTIVATION_CRON = "2 2 2 * * *";
    private static final String INVOICE_CRON = "0 0 0 1 * *";
    private static final String OVERDUE_CRON = "1 1 1 * * *";

    @Autowired
    private BillingService billingService;

    @Async
    @Scheduled(cron = DEACTIVATION_CRON)
    @Transactional
    public void checkActiveAccounts() {
        billingService.checkUsersWithOverduePayment();
    }

    @Async
    @Scheduled(cron = INVOICE_CRON)
    @Transactional
    public void generateInvoices() {
        billingService.generateMonthlyInvoices();
    }

    @Async
    @Scheduled(cron = OVERDUE_CRON)
    @Transactional
    public void checkOverdueInvoices() {
        billingService.processOverduePayments();
    }

}
