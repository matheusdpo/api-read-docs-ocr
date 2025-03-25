package br.com.lumen.paypal.v1.thymeleaf.controller;

import br.com.lumen.paypal.v1.services.PayPalService;
import br.com.lumen.paypal.v1.thymeleaf.model.Product;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;

@Controller
public class PayPalTestController {

    @Autowired
    private PayPalService payPalService;

    private final List<Product> products = Arrays.asList(
            new Product(1, "iPhone 16 Pro Max", 9999.99, "phone.jpg"),
            new Product(2, "Notebook ASUS Zenbook 14 OLED UX3405MA", 8499.99, "notebook.jpg"),
            new Product(3, "Headphone Baseus", 299.99, "headphone.jpg"),
            new Product(4, "Smartwatch Samsung 2025", 799.99, "smartwatch.jpg"),
            new Product(5, "Câmera Canon usada", 1799.99, "camera.jpg"),
            new Product(24, "Caneca Rick e Morty", 169.99, "caneca.jpg")
    );


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(@RequestParam int productId) {
        try {
            Product product = products.stream()
                    .filter(p -> p.getId() == productId)
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            String successUrl = "http://localhost/payment/success";
            String cancelUrl = "http://localhost/payment/cancel";

            Payment payment = payPalService.createPayment(
                    product.getPrice(),
                    "BRL",
                    "paypal",
                    "sale",
                    "Compra: " + product.getName(),
                    cancelUrl,
                    successUrl);

            String approvalUrl = payment.getLinks().stream()
                    .filter(e -> e.getRel().equals("approval_url"))
                    .findFirst()
                    .get()
                    .getHref();

            return new RedirectView(approvalUrl);

        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("/payment/error");
        }
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam String paymentId,
            @RequestParam String PayerID,
            Model model) {

        try {
            Payment payment = payPalService.executePayment(paymentId, PayerID);
            model.addAttribute("payment", payment);
            return "paymentSuccess";

        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "redirect:/payment/error";
        }
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel(Model model) {
        model.addAttribute("message", "Pagamento cancelado pelo usuário");
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError(Model model) {
        model.addAttribute("error", "Ocorreu um erro durante o pagamento");
        return "paymentError";
    }
}