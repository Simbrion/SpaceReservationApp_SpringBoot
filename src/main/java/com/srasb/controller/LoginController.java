package com.srasb.controller;

import com.srasb.model.dto.CustomerDto;
import com.srasb.repository.UserRepository;
import com.srasb.service.customerservice.CustomerEntityService;
import com.srasb.service.userservice.UserAuthenticationService;
import com.srasb.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserAuthenticationService userDetailsService;
    public final CustomerEntityService customerEntityService;
    private final UserRepository userRepository;

    private static final String ADMIN_USERNAME = "Admin";

    @GetMapping("/admin-login")
    public ResponseEntity<String> loginCustomer() {

        if (userRepository.findByUsername(ADMIN_USERNAME).isEmpty()) {
            userDetailsService.loadUserByUsername(ADMIN_USERNAME);
        }

        final String jwt = jwtUtil.generateToken(ADMIN_USERNAME);
        return ResponseEntity.ok("Session started for the administrator.\nJWT: " + jwt);
    }


    @PostMapping("/customer-login")
    public ResponseEntity<String> loginCustomer(@Valid @RequestBody CustomerDto customerDto,
                                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors());
        }

        if (customerEntityService.findByName(customerDto.getName())) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(customerDto.getName());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok("Session started for the customer: "
                    + customerDto.getName()
                    + ", Token: "
                    + jwt);
        }

        if (customerEntityService.findByName(customerDto.getName())) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(customerDto.getName());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok("Session started for existing customer: "
                                                                    + customerDto.getName()
                                                                    + "\nJWT: "
                                                                    + jwt);
        }

        return ResponseEntity.badRequest().body("There is no registered user with such name. Please register.");

    }

}
