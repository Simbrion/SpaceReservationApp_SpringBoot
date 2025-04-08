package com.srasb.controller.customercontrollers;

import com.srasb.controller.CustomerLoginController;
import com.srasb.service.reservationservice.ReservationEntityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationDeleteController {

    private final ReservationEntityService reservationEntityService;


    @DeleteMapping("/reservations/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id, HttpSession session) {

        Integer customerId = (Integer) session.getAttribute(CustomerLoginController.CUSTOMER_ID_ATTRIBUTE);

        if (customerId == null) {
            return ResponseEntity.badRequest().body("Customer login required!");
        }

        if (reservationEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body("There is no reservation with such id.");
        }

        reservationEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body("The reservation has been successfully deleted!");

    }


}
