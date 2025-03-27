package com.lumen.billing.v1.services;

import com.lumen.billing.v1.entities.InvoiceEntity;
import com.lumen.billing.v1.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    //sendInvoice
    public void sendInvoice(UserEntity user, InvoiceEntity invoiceEntity) {
        //envia para fila de invoice padrao
    }

    //sendPaymentReminder
    public void sendPaymentReminder(UserEntity user, InvoiceEntity invoiceEntity) {
        //envia para fila de cobranca
    }
}
