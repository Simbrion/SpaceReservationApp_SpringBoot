package com.srasb.controller.admincontrollers;

import com.srasb.model.dto.CustomerDto;
import com.srasb.service.customerservice.CustomerDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerSendListController {

    private final CustomerDtoService customerDtoService;


    @GetMapping("/customers/show")
    public ResponseEntity<List<CustomerDto>> showCustomers() {
           List<CustomerDto> customerDtoList = customerDtoService.getDtoList();

        if (customerDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);

    }


}
