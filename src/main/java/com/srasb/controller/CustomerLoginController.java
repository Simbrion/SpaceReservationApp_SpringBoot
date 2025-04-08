package com.srasb.controller;

import com.srasb.model.dto.CustomerDto;
import com.srasb.model.entity.CustomerEntity;
import com.srasb.service.customerservice.CustomerEntityService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerLoginController {

    public final CustomerEntityService customerEntityService;

    public static final String CUSTOMER_ID_ATTRIBUTE = "CustomerId";

    @PostMapping("/customers/login")
    public ResponseEntity<String> loginCustomer(@Valid @RequestBody CustomerDto customerDto,
                                                                    BindingResult bindingResult,
                                                                    HttpSession session) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors());
        }


        if (customerEntityService.existsByName(customerDto.getName())) {
            session.setAttribute(CUSTOMER_ID_ATTRIBUTE, customerEntityService.getEntityIdByName(customerDto.getName()));
            session.setAttribute(AdminLogin.ADMIN_SESSION_ATTRIBUTE, false);
            return ResponseEntity.ok("Session started for existing customer: "
                                                                    + customerDto.getName()
                                                                    + ", customer ID: "
                                                                    + session.getAttribute(CUSTOMER_ID_ATTRIBUTE));
        }

        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(customerDto.getName());
        System.out.println(newCustomer.getName());
        customerEntityService.addCustomer(newCustomer);
        session.setAttribute(CUSTOMER_ID_ATTRIBUTE, customerEntityService.getEntityIdByName(customerDto.getName()));
        return ResponseEntity.ok("Session started for new customer: "
                                    + newCustomer.getName()
                                    + ", customer ID: "
                                    + session.getAttribute(CUSTOMER_ID_ATTRIBUTE));
    }


}
