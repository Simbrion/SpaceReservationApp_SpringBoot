package com.srasb.controller.admincontrollers;

import com.srasb.service.customerservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerDeleteController {

    private final CustomerEntityService customerEntityService;


    @DeleteMapping("/customers/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {

        if (customerEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no customer with such id.");
        }

        customerEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body("The customer has been successfully deleted!");

    }


}
