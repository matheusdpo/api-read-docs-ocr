//package com.lumen.paypal.v1.thymeleaf.controller;
//
//import com.lumen.paypal.v1.entities.PaymentsEntity;
//import com.lumen.paypal.v1.entities.UserEntity;
//import com.lumen.paypal.v1.enums.PaymentStatusEnum;
//import com.lumen.paypal.v1.repositories.PaymentsRepository;
//import com.lumen.paypal.v1.repositories.UserRepository;
//import com.lumen.paypal.v1.services.PayPalService;
//import com.lumen.paypal.v1.thymeleaf.model.Product;
//import com.paypal.api.payments.Payment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.view.RedirectView;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//
//@Controller
//public class PayPalTestController {
//
//    @Autowired
//    private PayPalService payPalService;
//
//    @Autowired
//    private PaymentsRepository paymentsRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private final List<Product> products = Arrays.asList(
//            new Product(1, "iPhone 16 Pro Max", 9999.99, "phone.jpg"),
//            new Product(2, "Notebook ASUS Zenbook 14 OLED UX3405MA", 8499.99, "notebook.jpg"),
//            new Product(3, "Headphone Baseus", 299.99, "headphone.jpg"),
//            new Product(4, "Smartwatch Samsung 2025", 799.99, "smartwatch.jpg"),
//            new Product(5, "Câmera Canon usada", 1799.99, "camera.jpg"),
//            new Product(24, "Caneca Rick e Morty", 169.99, "caneca.jpg")
//    );
//
//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("products", products);
//        return "index";
//    }
//
//    @PostMapping("/payment/create")
//    public RedirectView createPayment(@RequestParam int productId,
//                                      RedirectAttributes redirectAttributes) {
//        try {
//            Product product = products.stream()
//                    .filter(p -> p.getId() == productId)
//                    .findFirst()
//                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
//
//            UserEntity user = userRepository.findById(1L)
//                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
//
//            PaymentsEntity paymentRecord = new PaymentsEntity();
//            paymentRecord.setAmount(product.getPrice());
//            paymentRecord.setCurrency("BRL");
//            paymentRecord.setDescription("Compra: " + product.getName());
//            paymentRecord.setUser(user);
//            paymentRecord.setPaymentStatus(PaymentStatusEnum.CREATED.getPaymentStatus());
//            paymentRecord.setProcessingDate(LocalDateTime.now());
//            paymentRecord = paymentsRepository.save(paymentRecord);
//
//            String successUrl = "http://localhost:8082/payment/success?paymentDbId=" + paymentRecord.getId();
//            String cancelUrl = "http://localhost:8082/payment/cancel?paymentDbId=" + paymentRecord.getId();
//
//            Payment payment = payPalService.createPayment(
//                    product.getPrice(),
//                    "BRL",
//                    "paypal",
//                    "sale",
//                    "Compra: " + product.getName(),
//                    cancelUrl,
//                    successUrl);
//
//            paymentRecord.setTransactionId(payment.getId());
//            paymentsRepository.save(paymentRecord);
//
//            String approvalUrl = payment.getLinks().stream()
//                    .filter(e -> e.getRel().equals("approval_url"))
//                    .findFirst()
//                    .orElseThrow()
//                    .getHref();
//
//            return new RedirectView(approvalUrl);
//
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", "Erro ao criar pagamento: " + e.getMessage());
//            return new RedirectView("/payment/error");
//        }
//    }
//
//    @GetMapping("/payment/success")
//    public String paymentSuccess(
//            @RequestParam String paymentDbId,
//            @RequestParam String paymentId,
//            @RequestParam(name = "PayerID") String payerId
//    ) {
//
//        try {
//            Payment payment = payPalService.executePayment(paymentId, payerId);
//
//            PaymentsEntity paymentRecord = paymentsRepository.findById(Long.valueOf(paymentDbId))
//                    .orElseThrow(() -> new RuntimeException("Registro de pagamento não encontrado"));
//
//            paymentRecord.setPaymentStatus(payment.getState());
//            paymentRecord.setPayerId(payerId);
//            paymentRecord.setProcessingDate(LocalDateTime.now());
//            paymentsRepository.save(paymentRecord);
//
//            return "paymentSuccess";
//
//        } catch (Exception e) {
//            paymentsRepository.findById(Long.valueOf(paymentDbId)).ifPresent(p -> {
//                p.setPaymentStatus(PaymentStatusEnum.ERROR.getPaymentStatus());
//                paymentsRepository.save(p);
//            });
//
//            return "paymentError";
//        }
//    }
//
//    @GetMapping("/payment/cancel")
//    public String paymentCancel(@RequestParam Long paymentDbId, Model model) {
//        paymentsRepository.findById(paymentDbId).ifPresent(p -> {
//            p.setPaymentStatus(PaymentStatusEnum.CANCELLED.getPaymentStatus());
//            p.setProcessingDate(LocalDateTime.now());
//            paymentsRepository.save(p);
//        });
//
//        model.addAttribute("message", "Pagamento cancelado pelo usuário");
//        return "paymentCancel";
//    }
//
//    @GetMapping("/payment/error")
//    public String paymentError(@ModelAttribute("error") String error, Model model) {
//        if (!model.containsAttribute("error")) {
//            model.addAttribute("error", "Ocorreu um erro durante o processamento do pagamento");
//        }
//        return "paymentError";
//    }
//}