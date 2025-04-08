package com.srasb.service.spaceservice;

import com.srasb.model.dto.SpaceDto;
import com.srasb.model.entity.SpaceEntity;
import com.srasb.repository.SpaceRepository;
import com.srasb.service.EntityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpaceEntityService implements EntityService<SpaceEntity, SpaceDto> {

    public final SpaceRepository spaceRepository;

    public SpaceEntity getEntityFromDto (SpaceDto spaceDto) {
        SpaceEntity newSpaceEntity = new SpaceEntity();
        newSpaceEntity.setName(spaceDto.getName());
        newSpaceEntity.setTypeOfSpace(spaceDto.getTypeOfSpace());
        newSpaceEntity.setPrice(spaceDto.getPrice());
        return newSpaceEntity;
    }

    @Transactional
    public void addEntityBasedOn(SpaceDto spaceDto) {
        spaceRepository.save(getEntityFromDto(spaceDto));
    }

    @Transactional
    public void deleteEntityById(int id) {
        spaceRepository.deleteById(id);
    }

    public boolean isSaved(SpaceDto spaceDto) {
        return spaceRepository.existsByName(spaceDto.getName());
    }

    public boolean existsById(int spaceId) {
        return spaceRepository.findById(spaceId).isPresent();
    }

    @Transactional
    public boolean changeSpaceBasedOn(SpaceDto spaceDto, int id) {
        Optional<SpaceEntity> spaceEntityOptional = spaceRepository.findById(id);

        if (spaceEntityOptional.isEmpty()) {
            return false;
        }

        SpaceEntity spaceEntity = new SpaceEntity();
        spaceEntity.setId(id);
        spaceEntity.setName(spaceDto.getName());
        spaceEntity.setPrice(spaceDto.getPrice());
        spaceEntity.setTypeOfSpace(spaceDto.getTypeOfSpace());
        spaceRepository.save(spaceEntity);
        return true;
    }

    public SpaceEntity getEntityById(int id) {
        return spaceRepository.findById(id).orElse(null);
    }

}
