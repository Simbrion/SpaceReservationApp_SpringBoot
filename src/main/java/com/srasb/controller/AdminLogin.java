package com.srasb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AdminLogin {

    public static final String ADMIN_SESSION_ATTRIBUTE = "AdminLogin";

    @GetMapping("/admin/login")
    public ResponseEntity<String> loginCustomer(HttpSession session) {
        session.setAttribute(CustomerLoginController.CUSTOMER_ID_ATTRIBUTE, null);
        session.setAttribute(ADMIN_SESSION_ATTRIBUTE, true);
        return ResponseEntity.ok("Session started for the admin!");
    }


}