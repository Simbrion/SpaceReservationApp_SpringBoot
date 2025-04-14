package com.srasb.controller;

import com.srasb.model.dto.CustomerDto;
import com.srasb.model.dto.ReservationDto;
import com.srasb.model.dto.SpaceDto;
import com.srasb.model.entity.CustomerEntity;
import com.srasb.model.entity.ReservationEntity;
import com.srasb.model.entity.SpaceEntity;
import com.srasb.service.DtoService;
import com.srasb.service.customerservice.CustomerEntityService;
import com.srasb.service.spaceservice.SpaceEntityService;
import com.srasb.util.messagecreators.MessageCreator;
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
    private final DtoService<SpaceDto, SpaceEntity> spaceDtoService;
    private final CustomerEntityService customerEntityService;
    private final DtoService<CustomerDto, CustomerEntity> customerDtoService;
    private final DtoService<ReservationDto, ReservationEntity> reservationDtoService;
    private final MessageCreator messageCreator;


    @GetMapping("/show-reservations")
    public ResponseEntity<List<ReservationDto>> sendListOfReservations() {
        System.out.println("!!!");
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
            return ResponseEntity.badRequest().body(messageCreator.noCustomerWithIdMessage());
        }

        customerEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body(messageCreator.customerDeletedMessage());
    }

    @PutMapping("change-space/{id}")
    public ResponseEntity<String> changeSpace(@PathVariable int id,
                                              @Valid @RequestBody SpaceDto spaceDto,
                                              BindingResult bindingResult) {

        if (spaceEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body(messageCreator.noSpaceWithIdMessage());
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(messageCreator.validationFailedMessage() + bindingResult.getAllErrors().toString());
        }

        if (spaceEntityService.changeSpaceBasedOn(spaceDto, id)) {
            return ResponseEntity.badRequest().body(messageCreator.spaceChangedMessage());
        }

        return ResponseEntity.badRequest().body(messageCreator.spaceAddingErrorMessage());
    }

    @PostMapping("create-space")
    public ResponseEntity<String> createSpace(@Valid @RequestBody SpaceDto spaceDto,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(messageCreator.validationFailedMessage() + bindingResult.getAllErrors().toString());
        }

        if (spaceEntityService.isSaved(spaceDto)) {
            return ResponseEntity.badRequest().body(messageCreator.spaceAlreadyExistsMessage());
        }

        spaceEntityService.addEntityBasedOn(spaceDto);
        return ResponseEntity.ok().body(messageCreator.spaceSavedMessage());
    }

    @DeleteMapping("delete-space/{id}")
    public ResponseEntity<String> deleteSpace(@PathVariable int id) {

        if (spaceEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body(messageCreator.noSpaceWithIdMessage());
        }

        spaceEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body(messageCreator.spaceDeletedMessage());
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
