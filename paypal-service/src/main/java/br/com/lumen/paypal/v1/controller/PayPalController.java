package br.com.lumen.paypal.v1.controller;

import br.com.lumen.paypal.v1.services.PayPalService;
import br.com.lumen.paypal.v1.utils.LogUtils;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/paypal")
public class PayPalController {

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private LogUtils logger;

    @PostMapping("/payment/create")
    public ResponseEntity<?> createPayment(@RequestParam Double amount,
                                           @RequestParam String currency,
                                           @RequestParam String method,
                                           @RequestParam String intent,
                                           @RequestParam String description) {
        try {
            logger.info("Creating payment");
            logger.info(String.format("Amount: %.2f, Currency: %s, Method: %s, Intent: %s, Description: %s",
                    amount, currency, method, intent, description));

            String sucessUrl = "http://localhost:8082/payment/success";
            String cancelUrl = "http://localhost:8082/payment/cancel";

            Payment payment = payPalService.createPayment(amount,
                    currency,
                    method,
                    intent,
                    description,
                    cancelUrl,
                    sucessUrl);

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
            @RequestParam String payerId) {
        try {
            logger.info(String.format("Payment success: PaymentId: %s, PayerId: %s", paymentId, payerId));
            Payment payment = payPalService.executePayment(paymentId, payerId);
            return ResponseEntity.ok().body(payment.getState());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity
                    .internalServerError()
                    .body("An error occurred while executing payment");
        }
    }
}