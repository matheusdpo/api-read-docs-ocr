package com.lumen.mailsender.v1.service;

import com.lumen.mailsender.v1.models.InvoiceMessageModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Value("${email.from}")
    private String mailFrom;

    public void sendMailInvoice(InvoiceMessageModel invoiceMessageModel) throws MessagingException {
        String subject = String.format("Invoice #%s – Due %s", invoiceMessageModel.getInvoiceNumber(), invoiceMessageModel.getDueDate());
        String htmlContent = emailTemplateService.buildInvoiceMessage(invoiceMessageModel);
        sendHtmlEmail(invoiceMessageModel, subject, htmlContent);
    }

    public void sendMailReminder(InvoiceMessageModel invoiceMessageModel) throws MessagingException {
        String subject = String.format("Reminder: Overdue Invoice #%s - Payment Due %s", invoiceMessageModel.getInvoiceNumber(), invoiceMessageModel.getDueDate());
        String htmlContent = emailTemplateService.buildReminderMessage(invoiceMessageModel);
        sendHtmlEmail(invoiceMessageModel, subject, htmlContent);
    }

    private void sendHtmlEmail(InvoiceMessageModel invoiceMessageModel, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(mailFrom);
        helper.setTo(invoiceMessageModel.getEmail());
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
