package com.lumen.paypal.v1.controller;

import com.lumen.paypal.v1.entities.PaymentsEntity;
import com.lumen.paypal.v1.entities.UserEntity;
import com.lumen.paypal.v1.enums.PaymentStatusEnum;
import com.lumen.paypal.v1.exceptions.PayPalServiceException;
import com.lumen.paypal.v1.repositories.PaymentsRepository;
import com.lumen.paypal.v1.repositories.UserRepository;
import com.lumen.paypal.v1.services.PayPalService;
import com.lumen.paypal.v1.utils.LogUtils;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/v1/paypal")
public class PayPalController {

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogUtils logger;

    @PostMapping("/payment/create")
    public ResponseEntity<?> createPayment(@RequestParam Double amount,
                                           @RequestParam String currency,
                                           @RequestParam String method,
                                           @RequestParam String intent,
                                           @RequestParam String description,
                                           @RequestParam Long userId) {
        try {
            logger.info("Creating payment");
            logger.info(String.format("Amount: %.2f, Currency: %s, Method: %s, Intent: %s, Description: %s",
                    amount, currency, method, intent, description));

            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new PayPalServiceException("Payment record not found"));

            PaymentsEntity paymentRecord = new PaymentsEntity();
            paymentRecord.setAmount(amount);
            paymentRecord.setCurrency(currency);
            paymentRecord.setPaymentMethod(method);
            paymentRecord.setIntent(intent);
            paymentRecord.setDescription(description);
            paymentRecord.setUser(userEntity);
            paymentRecord.setPaymentStatus(PaymentStatusEnum.CREATED.getPaymentStatus());
            paymentRecord.setProcessingDate(LocalDateTime.now());

            paymentRecord = paymentsRepository.save(paymentRecord);

            String dbIdUrl = "?paymentDbId=" + paymentRecord.getId();

            String sucessUrl = "http://localhost:8082/api/v1/paypal/payment/success" + dbIdUrl;
            String cancelUrl = "http://localhost:8082/api/v1/paypal/payment/cancel" + dbIdUrl;

            Payment payment = payPalService.createPayment(amount,
                    currency,
                    method,
                    intent,
                    description,
                    cancelUrl,
                    sucessUrl);

            paymentRecord.setTransactionId(payment.getId());
            paymentsRepository.save(paymentRecord);

            String href = payment.getLinks().stream()
                    .filter(e -> e.getRel().equals("approval_url"))
                    .findFirst()
                    .get()
                    .getHref();

            return ResponseEntity
                    .ok()
                    .body(href);
        } catch (PayPalRESTException e) {
            return ResponseEntity
                    .internalServerError()
                    .body("An error occurred while creating payment");
        }
    }

    @GetMapping("/payment/success")
    public ResponseEntity<?> paymentSuccess(
            @RequestParam String paymentId,
            @RequestParam String PayerID,
            @RequestParam Long paymentDbId) {

        PaymentsEntity paymentRecord = paymentsRepository.findById(paymentDbId)
                .orElseThrow(() -> new PayPalServiceException("Payment record not found"));

        try {
            logger.info(String.format("Payment success: PaymentId: %s, PayerId: %s", paymentId, PayerID));

            Payment payment = payPalService.executePayment(paymentId, PayerID);

            paymentRecord.setPaymentStatus(payment.getState().toUpperCase());
            paymentRecord.setPayerId(PayerID);
            paymentRecord.setProcessingDate(LocalDateTime.now());

            paymentsRepository.save(paymentRecord);

            //todo send mail to user about payment success

            return ResponseEntity.ok().body(payment.getState());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            paymentRecord.setPaymentStatus(PaymentStatusEnum.ERROR.getPaymentStatus());
            paymentsRepository.save(paymentRecord);

            return ResponseEntity
                    .internalServerError()
                    .body("An error occurred while executing payment");
        }
    }

    @GetMapping("/payment/cancel")
    public ResponseEntity<?> paymentCancel(@RequestParam Long paymentDbId) {
        logger.info("Payment cancelled");
        PaymentsEntity paymentRecord = paymentsRepository.findById(paymentDbId)
                .orElseThrow(() -> new PayPalServiceException("Payment record not found"));

        paymentRecord.setPaymentStatus(PaymentStatusEnum.CANCELLED.getPaymentStatus());
        paymentRecord.setProcessingDate(LocalDateTime.now());
        paymentsRepository.save(paymentRecord);

        //todo send mail to user about payment cancellation

        return ResponseEntity.ok().body("Payment cancelled");
    }
}