package br.com.lumen.paypal.v1.services;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PayPalService {

    @Autowired
    private APIContext apiContext;

    public Payment createPayment(Double amount,
                                 String currency,
                                 String method,
                                 String intent,
                                 String description,
                                 String cancelUrl,
                                 String successUrl) throws PayPalRESTException {

        Amount amountObj = new Amount();
        amountObj.setCurrency(currency);
        amountObj.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", amount));

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amountObj);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setPayer(payer);
        payment.setIntent(intent);
        payment.setTransactions(transactionList);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);

        return payment.execute(apiContext, paymentExecute);
    }
}
