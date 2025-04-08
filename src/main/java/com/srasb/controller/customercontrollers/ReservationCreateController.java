package com.srasb.controller.customercontrollers;

import com.srasb.controller.CustomerLoginController;
import com.srasb.model.dto.ReservationDto;
import com.srasb.model.entity.ReservationEntity;
import com.srasb.service.customerservice.CustomerEntityService;
import com.srasb.service.reservationservice.ReservationEntityService;
import com.srasb.service.spaceservice.SpaceEntityService;
import com.srasb.util.TimeOverlapChecker;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ReservationCreateController {

    private final ReservationEntityService reservationEntityService;
    private final CustomerEntityService customerEntityService;
    private final SpaceEntityService spaceEntityService;
    private final TimeOverlapChecker timeOverlapChecker;


    @PostMapping("/reservations/create")
    public ResponseEntity<String> createReservation(@Valid @RequestBody ReservationDto reservationDto,
                                                                        BindingResult bindingResult,
                                                                        HttpSession session) {

        Integer customerId = (Integer) session.getAttribute(CustomerLoginController.CUSTOMER_ID_ATTRIBUTE);

        if (customerId == null) {
            return ResponseEntity.badRequest().body("Customer login required!");
        }

        if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body("Validation failed: " + bindingResult.getAllErrors().toString());
        }

        if (!spaceEntityService.existsById(reservationDto.getSpaceId())) {
            return ResponseEntity.badRequest().body("The space chosen for the reservation does not exist!");
        }

        ReservationEntity reservationEntity = reservationEntityService.getEntityFromDto(reservationDto);
        reservationEntity.setCustomerEntity(customerEntityService.getEntityById(customerId));
        reservationEntity.setSpaceEntity(spaceEntityService.getEntityById(reservationDto.getSpaceId()));
        LocalDateTime startOfNewReservation = LocalDateTime.of(reservationDto.getDate(), reservationDto.getStartTime());
        LocalDateTime endOfNewReservation = LocalDateTime.of(reservationDto.getDate(), reservationDto.getEndTime());

        if (timeOverlapChecker.startTimeOverlaps(startOfNewReservation, reservationEntity)) {
            return ResponseEntity.badRequest().body("The start time of the reservation overlaps with the existing reservation!");
        }

        if (timeOverlapChecker.endTimeOverlaps(endOfNewReservation, reservationEntity)) {
            return ResponseEntity.badRequest().body("The end time of the reservation overlaps with the existing reservation!");
        }

        reservationDto.setCustomerId((Integer) session.getAttribute(CustomerLoginController.CUSTOMER_ID_ATTRIBUTE));

        reservationEntityService.addEntityBasedOn(reservationDto);
            return ResponseEntity.ok().body("The reservation has been successfully saved!");
    }


}
