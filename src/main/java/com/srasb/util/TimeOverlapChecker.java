package com.srasb.util;

import com.srasb.model.entity.ReservationEntity;
import com.srasb.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class TimeOverlapChecker {

    private final ReservationRepository reservationsRepository;

    public boolean startTimeOverlaps(LocalDateTime startOfNewReservation, ReservationEntity newReservationEntity) {
        if (reservationsRepository.count() == 0) return false;
        for (ReservationEntity existingReservationEntity : reservationsRepository.findAll()) {
            if (!(existingReservationEntity.getSpaceEntity().equals(newReservationEntity.getSpaceEntity()))) continue;
            LocalDateTime startOfExistingReservation = LocalDateTime.of(existingReservationEntity.getDate(), existingReservationEntity.getStartTime());
            LocalDateTime endOfExistingReservation = LocalDateTime.of(existingReservationEntity.getDate(), existingReservationEntity.getEndTime());
            if (startOfNewReservation.isAfter(startOfExistingReservation) && startOfNewReservation.isBefore(endOfExistingReservation)) return true;
            if (startOfNewReservation.isEqual(startOfExistingReservation) || startOfNewReservation.isEqual(endOfExistingReservation)) return true;
        }
        return false;
    }

    public boolean endTimeOverlaps(LocalDateTime endOfNewReservation, ReservationEntity newReservationEntity) {
        if (reservationsRepository.count() == 0) return false;
        for (ReservationEntity existingReservationEntity : reservationsRepository.findAll()) {
            if (!(existingReservationEntity.getSpaceEntity().equals(newReservationEntity.getSpaceEntity()))) continue;
            LocalDateTime startOfExistingReservation = LocalDateTime.of(existingReservationEntity.getDate(), existingReservationEntity.getStartTime());
            LocalDateTime endOfExistingReservation = LocalDateTime.of(existingReservationEntity.getDate(), existingReservationEntity.getEndTime());
            LocalDateTime startOfNewReservation = LocalDateTime.of(newReservationEntity.getDate(), newReservationEntity.getStartTime());
            if ((startOfNewReservation.isBefore(endOfExistingReservation)) && endOfNewReservation.isAfter(startOfExistingReservation)) return true;
            if ((endOfNewReservation.isEqual(startOfExistingReservation)) || (endOfNewReservation).isEqual(endOfExistingReservation)) return true;
        }
        return false;

    }
}
