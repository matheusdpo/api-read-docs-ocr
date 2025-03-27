package com.lumen.billing.v1.controller;

import com.lumen.billing.v1.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;
}
