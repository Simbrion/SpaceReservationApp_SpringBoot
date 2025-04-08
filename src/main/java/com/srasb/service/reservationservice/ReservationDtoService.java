package com.srasb.service.reservationservice;

import com.srasb.model.dto.ReservationDto;
import com.srasb.model.entity.ReservationEntity;
import com.srasb.repository.ReservationRepository;
import com.srasb.service.DtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationDtoService implements DtoService<ReservationDto, ReservationEntity> {

    public final ReservationRepository reservationRepository;

    public List<ReservationDto> getDtoList() {
        List<ReservationDto> reservationDtoList = new ArrayList<>();
        for (ReservationEntity reservationEntity : reservationRepository.findAll()) {
            reservationDtoList.add(this.getDto(reservationEntity));
        }
        return reservationDtoList;
    }

    public ReservationDto getDto(ReservationEntity reservationEntity) {
        return  ReservationDto.builder()
                .id(reservationEntity.getId())
                .spaceName(reservationEntity.getSpaceEntity().getName())
                .spaceId(reservationEntity.getSpaceEntity().getId())
                .customerName(reservationEntity.getCustomerEntity().getName())
                .customerId(reservationEntity.getCustomerEntity().getId())
                .date(reservationEntity.getDate())
                .startTime(reservationEntity.getStartTime())
                .endTime(reservationEntity.getEndTime())
                .build();
    }

}
