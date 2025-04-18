package com.srasb.controller;

import com.srasb.model.dto.ReservationDto;
import com.srasb.model.entity.ReservationEntity;
import com.srasb.service.customerservice.CustomerEntityService;
import com.srasb.service.reservationservice.ReservationDtoService;
import com.srasb.service.reservationservice.ReservationEntityService;
import com.srasb.service.spaceservice.SpaceEntityService;
import com.srasb.util.TimeOverlapChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final ReservationEntityService reservationEntityService;
    private final CustomerEntityService customerEntityService;
    private final SpaceEntityService spaceEntityService;
    private final TimeOverlapChecker timeOverlapChecker;
    private final ReservationDtoService reservationDtoService;


    @PostMapping("create-reservation")
    public ResponseEntity<String> createReservation(@Valid @RequestBody ReservationDto reservationDto,
                                                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors().toString());
        }

        if (!spaceEntityService.existsById(reservationDto.getSpaceId())) {
            return ResponseEntity.badRequest().body("The space chosen for the reservation does not exist!");
        }

        ReservationEntity reservationEntity = reservationEntityService.getEntityFromDto(reservationDto);
        reservationEntity.setCustomerEntity(customerEntityService.getEntityById(getAuthenticatedCustomerId()));
        reservationEntity.setSpaceEntity(spaceEntityService.getEntityById(reservationDto.getSpaceId()));
        LocalDateTime startOfNewReservation = LocalDateTime.of(reservationDto.getDate(), reservationDto.getStartTime());
        LocalDateTime endOfNewReservation = LocalDateTime.of(reservationDto.getDate(), reservationDto.getEndTime());

        if (timeOverlapChecker.startTimeOverlaps(startOfNewReservation, reservationEntity)) {
            return ResponseEntity.badRequest().body("The start time of the reservation overlaps with the existing reservation!");
        }

        if (timeOverlapChecker.endTimeOverlaps(endOfNewReservation, reservationEntity)) {
            return ResponseEntity.badRequest().body("The end time of the reservation overlaps with the existing reservation!");
        }

        reservationDto.setCustomerId(getAuthenticatedCustomerId());

        reservationEntityService.addEntityBasedOn(reservationDto);
            return ResponseEntity.ok().body("The reservation has been successfully saved!");
    }

    @DeleteMapping("delete-reservation/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {

        final String loggedInCustomerName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (reservationEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no reservation with such id.");
        }

        if (!Objects.equals(reservationEntityService.getEntityById(id).getCustomerEntity().getName(), loggedInCustomerName)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not allowed to delete this reservation.");
        }

        reservationEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body("The reservation has been successfully deleted!");

    }

    @GetMapping("show-reservations")
    public ResponseEntity<List<ReservationDto>> showCustomerReservations() {

        List<ReservationDto> reservationDtoList = reservationDtoService.getDtoList()
                .stream()
                .filter(reservationDto -> reservationDto.getCustomerId()== getAuthenticatedCustomerId())
                .toList();

        if (reservationDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);

    }

   private int getAuthenticatedCustomerId() {
       final String customerName =  SecurityContextHolder.getContext().getAuthentication().getName();
       return customerEntityService.getEntityIdByName(customerName);
   }


}
