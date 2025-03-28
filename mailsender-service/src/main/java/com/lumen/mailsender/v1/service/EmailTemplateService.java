package com.lumen.mailsender.v1.service;

import com.lumen.mailsender.v1.models.InvoiceMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailTemplateService {

    @Autowired
    private TemplateEngine templateEngine;

    public String buildInvoiceMessage(InvoiceMessageModel invoiceMessageModel) {
        Context context = createCommonContext(invoiceMessageModel);
        context.setVariable("isReminder", false);
        return templateEngine.process("invoice-template", context);
    }

    public String buildReminderMessage(InvoiceMessageModel invoiceMessageModel) {
        Context context = createCommonContext(invoiceMessageModel);
        context.setVariable("isReminder", true);
        context.setVariable("daysOverdue", invoiceMessageModel.getDueDate());
        return templateEngine.process("reminder-template", context);
    }

//    public String buildSuccessPaymentMessage() {
//        Context context = createCommonContext(invoiceMessageModel);
//        context.setVariable("isReminder", false);
//        return templateEngine.process("success-payment-template", context);
//    }

    private Context createCommonContext(InvoiceMessageModel invoiceMessageModel) {
        Context context = new Context();
        context.setVariable("invoiceNumber", invoiceMessageModel.getInvoiceNumber());
        context.setVariable("dueDate", invoiceMessageModel.getDueDate());
        context.setVariable("clientName", invoiceMessageModel.getName());
        context.setVariable("amount", invoiceMessageModel.getTotalAmount());
        context.setVariable("email", invoiceMessageModel.getEmail());
        context.setVariable("description", invoiceMessageModel.getDescription());
        //add here more variables if needed
        return context;
    }
}
