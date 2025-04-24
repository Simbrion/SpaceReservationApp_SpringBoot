package com.srasb.controller;

import com.srasb.model.dto.ReservationDto;
import com.srasb.model.entity.ReservationEntity;
import com.srasb.service.DtoService;
import com.srasb.service.customerservice.CustomerEntityService;
import com.srasb.service.reservationservice.ReservationEntityService;
import com.srasb.service.spaceservice.SpaceEntityService;
import com.srasb.util.JwtUtil;
import com.srasb.util.messagecreators.MessageCreator;
import com.srasb.util.TimeOverlapChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final DtoService<ReservationDto, ReservationEntity> reservationDtoService;
    private final MessageCreator messageCreator;
    private final JwtUtil jwtUtil;


    @PostMapping("create-reservation")
    public ResponseEntity<String> createReservation(@Valid @RequestBody ReservationDto reservationDto,
                                                    BindingResult bindingResult,
                                                    @RequestHeader("Authorization") String authorizationHeader) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(messageCreator.validationFailedMessage() + bindingResult.getAllErrors().toString());
        }

        if (!spaceEntityService.existsById(reservationDto.getSpaceId())) {
            return ResponseEntity.badRequest().body(messageCreator.spaceForReservationNotExists());
        }




        ReservationEntity reservationEntity = reservationEntityService.getEntityFromDto(reservationDto);
        reservationEntity.setCustomerEntity(customerEntityService.getEntityById(getAuthenticatedCustomerId(authorizationHeader)));
        reservationEntity.setSpaceEntity(spaceEntityService.getEntityById(reservationDto.getSpaceId()));
        LocalDateTime startOfNewReservation = LocalDateTime.of(reservationDto.getDate(), reservationDto.getStartTime());
        LocalDateTime endOfNewReservation = LocalDateTime.of(reservationDto.getDate(), reservationDto.getEndTime());

        if (timeOverlapChecker.startTimeOverlaps(startOfNewReservation, reservationEntity)) {
            return ResponseEntity.badRequest().body(messageCreator.startTimeOverlapsMessage());
        }

        if (timeOverlapChecker.endTimeOverlaps(endOfNewReservation, reservationEntity)) {
            return ResponseEntity.badRequest().body(messageCreator.endTimeOverlapsMessage());
        }

        reservationDto.setCustomerId(getAuthenticatedCustomerId(authorizationHeader));

        reservationEntityService.addEntityBasedOn(reservationDto);
        return ResponseEntity.ok().body(messageCreator.reservationSavedMessage());
    }

    @DeleteMapping("delete-reservation/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {

        final String loggedInCustomerName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (reservationEntityService.getEntityById(id) == null) {
            return ResponseEntity.badRequest().body(messageCreator.noReservationWithIdMessage());
        }

        if (!Objects.equals(reservationEntityService.getEntityById(id).getCustomerEntity().getName(), loggedInCustomerName)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(messageCreator.noRightsToDeleteReservationMessage());
        }

        reservationEntityService.deleteEntityById(id);
        return ResponseEntity.ok().body(messageCreator.reservationDeletedMessage());
    }

    @GetMapping("show-reservations")
    public ResponseEntity<List<ReservationDto>> showCustomerReservations(@RequestHeader("Authorization") String authorizationHeader) {

        List<ReservationDto> reservationDtoList = reservationDtoService.getDtoList()
                .stream()
                .filter(reservationDto -> reservationDto.getCustomerId() == getAuthenticatedCustomerId(authorizationHeader))
                .toList();

        if (reservationDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(reservationDtoList, HttpStatus.OK);
    }

    @GetMapping("profile")
    public ResponseEntity<List<String>> showCustomerProfile(@RequestHeader("Authorization") String authorizationHeader)  {
            return new ResponseEntity<>(getAuthenticatedCustomerProfile(authorizationHeader), HttpStatus.OK);
    }

    private int getAuthenticatedCustomerId(String authorizationHeader) {
        String customerToken = jwtUtil.getTokenFromAuthorisationHeader(authorizationHeader);
        return Integer.parseInt(jwtUtil.getClaimFromToken(customerToken, "id"));
    }

    private List<String> getAuthenticatedCustomerProfile(String authorizationHeader) {
        String customerToken = jwtUtil.getTokenFromAuthorisationHeader(authorizationHeader);
        List<String> profileDataList = new ArrayList<>();
        profileDataList.add("Your name: " + jwtUtil.getClaimFromToken(customerToken, "name"));
        profileDataList.add("Your ID: " + jwtUtil.getClaimFromToken(customerToken, "id"));
        profileDataList.add("Your user role: " + jwtUtil.getClaimFromToken(customerToken, "role").substring(5));
        return  profileDataList;
    }


}
