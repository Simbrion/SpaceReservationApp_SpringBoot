package com.srasb.service.reservationservice;

import com.srasb.model.dto.CustomerDto;
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
        for (ReservationDto reservationDto : super.getDtoList()) {
            log.info("ReservationDto on the list: [id={}, customerId={}, customerName={}, spaceId={}, spaceName={}, startTime={}, endTime={}]",
                    reservationDto.getId(),
                    reservationDto.getCustomerId(),
                    reservationDto.getCustomerName(),
                    reservationDto.getSpaceId(),
                    reservationDto.getSpaceName(),
                    reservationDto.getStartTime(),
                    reservationDto.getEndTime());
        }
        return super.getDtoList();
    }

    @Override
    public ReservationDto getDto(ReservationEntity entity) {
        log.info("Producing ReservationDto from entity: [id={}, customerName={}, customerId={}, spaceName={}, spaceId={}, startTime={}, endTime={}]",
                entity.getId(),
                entity.getCustomerEntity().getName(),
                entity.getCustomerEntity().getId(),
                entity.getSpaceEntity().getName(),
                entity.getSpaceEntity().getId(),
                entity.getStartTime(),
                entity.getEndTime());
        return super.getDto(entity);
    }

}
