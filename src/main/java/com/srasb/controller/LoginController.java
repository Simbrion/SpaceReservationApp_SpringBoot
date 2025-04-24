package com.srasb.controller;

import com.srasb.model.securitymodel.CustomUserDetails;
import com.srasb.service.customerservice.CustomerEntityService;
import com.srasb.util.messagecreators.MessageCreator;
import com.srasb.service.userservice.UserAuthenticationService;
import com.srasb.service.userservice.UserRepositoryService;
import com.srasb.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserAuthenticationService userDetailsService;
    public final CustomerEntityService customerEntityService;
    private final UserRepositoryService userRepositoryService;
    private final MessageCreator messageCreator;

    private static final String ADMIN_USERNAME = "Admin";


    @GetMapping("/admin-login")
    public ResponseEntity<String> loginAdmin() {

        if (userRepositoryService.findByUsername(ADMIN_USERNAME).isEmpty()) {
            userDetailsService.loadUserByUsername(ADMIN_USERNAME);
        }

        final String jwt = jwtUtil.generateToken(userRepositoryService.findByUsername(ADMIN_USERNAME).get());
        return ResponseEntity.ok(messageCreator.sessionStartedAdminMessage() + "\nJWT: " + jwt);
    }


    @PostMapping("/customer-login")
    public ResponseEntity<String> loginCustomer() {

        if (customerEntityService.findByName(getAuthenticateUserName())) {
            final CustomUserDetails customUserDetails = userDetailsService.loadUserByUsername(getAuthenticateUserName());
            final String jwt = jwtUtil.generateToken(userRepositoryService.findByUsername(customUserDetails.getUsername()).get());

            return ResponseEntity.ok(messageCreator.sessionStartedCustomerMessage()
                    + getAuthenticateUserName()
                    + "\nJWT: "
                    + jwt);
        }

        return ResponseEntity.badRequest().body(messageCreator.noRegisteredUserWithNameMessage());
    }

    private String getAuthenticateUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return customUserDetails.getUsername();
    }
}
