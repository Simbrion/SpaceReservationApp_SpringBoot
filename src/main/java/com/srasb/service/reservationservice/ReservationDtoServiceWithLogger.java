package com.srasb.service.reservationservice;

import com.srasb.model.dto.ReservationDto;
import com.srasb.model.entity.ReservationEntity;
import com.srasb.service.DtoServiceWithLogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
@Log4j2
public class ReservationDtoServiceWithLogger extends DtoServiceWithLogger<ReservationDto, ReservationEntity> {


    public ReservationDtoServiceWithLogger(ReservationDtoService dtoService) {
        super(dtoService);
    }


    @Override
    public List<ReservationDto> getDtoList() {
        log.info("ReservationDtoService produces Dto list.");
        return super.getDtoList();
    }

    @Override
    public ReservationDto getDto(ReservationEntity entity) {
        log.info("ReservationDtoService produces Dto on the basis of a reservation entity.");
        return super.getDto(entity);
    }

}
