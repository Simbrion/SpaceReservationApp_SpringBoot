package com.srasb.service.reservationservice;
import com.srasb.model.dto.ReservationDto;
import com.srasb.model.entity.ReservationEntity;
import com.srasb.repository.CustomerRepository;
import com.srasb.repository.ReservationRepository;
import com.srasb.repository.SpaceRepository;
import com.srasb.service.EntityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationEntityService implements EntityService<ReservationEntity, ReservationDto> {

    public final CustomerRepository customerRepository;
    public final SpaceRepository spaceRepository;
    public final ReservationRepository reservationRepository;


    @Override
    public ReservationEntity getEntityFromDto(ReservationDto dto) {
        ReservationEntity newReservationEntity = new ReservationEntity();
        newReservationEntity.setCustomerEntity(customerRepository.findById(dto.getCustomerId()).orElse(null));
        newReservationEntity.setSpaceEntity(spaceRepository.findById(dto.getSpaceId()).orElse(null));
        newReservationEntity.setDate(dto.getDate());
        newReservationEntity.setStartTime(dto.getStartTime());
        newReservationEntity.setEndTime(dto.getEndTime());
        return newReservationEntity;
    }

    @Override
    @Transactional
    public void addEntityBasedOn(ReservationDto reservationDto) {
        reservationRepository.save(getEntityFromDto(reservationDto));
    }

    @Override
    @Transactional
    public void deleteEntityById(int id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationEntity getEntityById(int id) {
        return reservationRepository.findById(id).orElse(null);
    }
}
