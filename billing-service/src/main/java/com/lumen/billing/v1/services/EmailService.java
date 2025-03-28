package com.lumen.billing.v1.services;

import com.lumen.billing.v1.entities.InvoiceEntity;
import com.lumen.billing.v1.entities.UserEntity;
import com.lumen.billing.v1.models.InvoiceMessageModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key.invoice}")
    private String routingKeyInvoice;

    @Value("${rabbitmq.routing.key.reminder}")
    private String routingKeyReminder;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //sendInvoice
    public void sendInvoice(UserEntity user, InvoiceEntity invoiceEntity) {
        InvoiceMessageModel invoiceMessageModel = invoiceMessageModelBuilder(user, invoiceEntity);

        rabbitTemplate.convertAndSend(exchangeName, routingKeyInvoice, invoiceMessageModel);
    }

    //sendPaymentReminder
    public void sendPaymentReminder(UserEntity user, InvoiceEntity invoiceEntity) {
        InvoiceMessageModel invoiceMessageModel = invoiceMessageModelBuilder(user, invoiceEntity);

        rabbitTemplate.convertAndSend(exchangeName, routingKeyReminder, invoiceMessageModel);
    }

    private InvoiceMessageModel invoiceMessageModelBuilder(UserEntity user, InvoiceEntity invoiceEntity) {
        InvoiceMessageModel invoiceMessageModel = invoiceMessageModelBuilder(user, invoiceEntity);

        invoiceMessageModel.setName(user.getName());
        invoiceMessageModel.setLastName(user.getLastName());
        invoiceMessageModel.setEmail(user.getEmail());
        invoiceMessageModel.setTotalAmount(invoiceEntity.getTotalAmount());
        invoiceMessageModel.setDescription(invoiceEntity.getDescription());
        invoiceMessageModel.setDueDate(invoiceEntity.getDueDate());
        invoiceMessageModel.setInvoiceNumber(invoiceEntity.getInvoiceNumber());

        return invoiceMessageModel;
    }
}
