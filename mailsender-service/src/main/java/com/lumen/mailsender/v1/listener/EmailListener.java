package com.lumen.mailsender.v1.listener;

import com.lumen.mailsender.v1.models.InvoiceMessageModel;
import com.lumen.mailsender.v1.service.EmailService;
import com.lumen.mailsender.v1.utils.LogUtils;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    private LogUtils logger;

    @RabbitListener(queues = "invoice")
    public void sendEmailInvoice(InvoiceMessageModel invoiceMessageModel) {
        try {
            emailService.sendMailInvoice(invoiceMessageModel);
        } catch (MessagingException e) {
            logger.error("Error sending email invoice", e);
        }
    }

    @RabbitListener(queues = "reminder")
    public void sendEmailReminder(InvoiceMessageModel invoiceMessageModel) throws MessagingException {
        try {
            emailService.sendMailReminder(invoiceMessageModel);
        } catch (MessagingException e) {
            logger.error("Error sending email invoice", e);
        }
    }
}
