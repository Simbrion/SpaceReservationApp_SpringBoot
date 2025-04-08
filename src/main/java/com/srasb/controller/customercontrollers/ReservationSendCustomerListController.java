package com.srasb.controller.customercontrollers;

import com.srasb.controller.CustomerLoginController;
import com.srasb.model.dto.ReservationDto;
import com.srasb.service.reservationservice.ReservationDtoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationSendCustomerListController {

    private final ReservationDtoService reservationDtoService;

    @GetMapping("/reservations/show-customer-reservations")
    public ResponseEntity<List<ReservationDto>> showCustomerReservations(HttpSession session) {

        Integer customerId = (Integer) session.getAttribute(CustomerLoginController.CUSTOMER_ID_ATTRIBUTE);

        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

           List<ReservationDto> reservationDtoList = reservationDtoService.getDtoList()
                          .stream()
                          .filter(reservationDto -> reservationDto.getCustomerId()==customerId)
                          .toList();

        if (reservationDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);

    }


}
