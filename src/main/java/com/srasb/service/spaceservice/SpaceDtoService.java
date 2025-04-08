package com.srasb.service.spaceservice;

import com.srasb.model.dto.SpaceDto;
import com.srasb.model.entity.SpaceEntity;
import com.srasb.repository.SpaceRepository;
import com.srasb.service.DtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceDtoService implements DtoService<SpaceDto, SpaceEntity> {

    public final SpaceRepository spaceRepository;

    public List<SpaceDto> getDtoList() {
        List<SpaceDto> spaceDtoList = new ArrayList<>();
        for (SpaceEntity spaceEntity : spaceRepository.findAll()) {
            spaceDtoList.add(this.getDto(spaceEntity));
        }
        return spaceDtoList;
    }

    public SpaceDto getDto(SpaceEntity spaceEntity) {
       return  SpaceDto.builder()
                          .id(spaceEntity.getId())
                          .name(spaceEntity.getName())
                          .price(spaceEntity.getPrice())
                          .typeOfSpace(spaceEntity.getTypeOfSpace())
                          .build();
    }


}
