package com.srasb.controller;

import com.srasb.model.dto.CustomerDto;
import com.srasb.model.dto.ReservationDto;
import com.srasb.model.dto.SpaceDto;
import com.srasb.service.customerservice.CustomerDtoService;
import com.srasb.service.customerservice.CustomerEntityService;
import com.srasb.service.reservationservice.ReservationDtoService;
import com.srasb.service.spaceservice.SpaceDtoService;
import com.srasb.service.spaceservice.SpaceEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final SpaceEntityService spaceEntityService;
    private final SpaceDtoService spaceDtoService;
    private final CustomerEntityService customerEntityService;
    private final CustomerDtoService customerDtoService;
    private final ReservationDtoService reservationDtoService;


    @GetMapping("/show-reservations")
    public ResponseEntity<List<ReservationDto>> sendListOfRerervations() {
        List<ReservationDto> reservationDtoList = reservationDtoService.getDtoList();

        if (reservationDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);

    }

    @GetMapping("/show-customers")
    public ResponseEntity<List<CustomerDto>> sendListOfCustomers() {
        List<CustomerDto> customerDtoList = customerDtoService.getDtoList();

        if (customerDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);

    }


    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {

        if (customerEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no customer with such id.");
        }

        customerEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body("The customer has been successfully deleted!");

    }

    @PutMapping("change-space/{id}")
    public ResponseEntity<String> changeSpace(@PathVariable int id,
                                              @Valid @RequestBody SpaceDto spaceDto,
                                              BindingResult bindingResult) {

        if (spaceEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no space with such id.");
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors().toString());
        }

        if (spaceEntityService.changeSpaceBasedOn(spaceDto, id)) {
            return ResponseEntity.badRequest().body("The space has been successfully changed!");
        }

        return ResponseEntity.badRequest().body("Something went wrong when trying to space the changed space to the database");
    }


    @PostMapping("create-space")
    public ResponseEntity<String> createSpace(@Valid @RequestBody SpaceDto spaceDto,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors().toString());
        }

        if (spaceEntityService.isSaved(spaceDto)) {
            return ResponseEntity.badRequest().body("Validation failed: the space with this name already exists!");
        }

        spaceEntityService.addEntityBasedOn(spaceDto);
        return ResponseEntity.ok().body("The space has been successfully saved!");
    }


    @DeleteMapping("delete-space/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable int id) {

        if (spaceEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no space with such id.");
        }

        spaceEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body("The space has been successfully deleted!");

    }

    @GetMapping("/show-spaces")
    public ResponseEntity<List<SpaceDto>> sendListOfSpaces() {

        List<SpaceDto> spaceDtoList = spaceDtoService.getDtoList();

        if (spaceDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(spaceDtoList, HttpStatus.OK);

    }


}
