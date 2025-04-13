package com.srasb.controller;

import com.srasb.model.dto.CustomerDto;
import com.srasb.model.entity.CustomerEntity;
import com.srasb.model.entity.UserRole;
import com.srasb.model.entity.UserEntity;
import com.srasb.repository.UserRepository;
import com.srasb.service.userservice.UserRepositoryService;
import com.srasb.service.customerservice.CustomerEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final CustomerEntityService customerEntityService;
    private final UserRepositoryService userRepositoryService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<String> loginCustomer(@Valid @RequestBody CustomerDto customerDto,
                                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors());
        }

        if (customerEntityService.isSaved(customerDto)) {
            return ResponseEntity.badRequest().body("Validation failed: the customer with this name already exists!");
        }

        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(customerDto.getName());
        System.out.println(newCustomer.getName());
        customerEntityService.addCustomer(newCustomer);

        UserEntity newUser = new UserEntity();
        newUser.setId(newCustomer.getId());
        newUser.setUsername(customerDto.getName());
        newUser.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        newUser.setUserRole(UserRole.ROLE_CUSTOMER);
        userRepositoryService.add(newUser);

        return ResponseEntity.ok("The customer has been registered: "
                                    + newCustomer.getName());
    }


}
