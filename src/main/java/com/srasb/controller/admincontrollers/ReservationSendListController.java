package com.srasb.controller.admincontrollers;

import com.srasb.model.dto.ReservationDto;
import com.srasb.service.reservationservice.ReservationDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationSendListController {

    private final ReservationDtoService reservationDtoService;


    @GetMapping("/reservations/show")
    public ResponseEntity<List<ReservationDto>> showSpaces() {
           List<ReservationDto> reservationDtoList = reservationDtoService.getDtoList();

        if (reservationDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);

    }


}
