package com.srasb.service.spaceservice;

import com.srasb.model.dto.SpaceDto;
import com.srasb.model.entity.SpaceEntity;
import com.srasb.service.DtoServiceWithLogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
@Log4j2
public class SpaceDtoServiceWithLogger extends DtoServiceWithLogger<SpaceDto, SpaceEntity> {


    public SpaceDtoServiceWithLogger(SpaceDtoService dtoService) {
        super(dtoService);
    }

    @Override
    public List<SpaceDto> getDtoList() {
        log.info("SpaceDtoService produces Dto list.");
        return super.getDtoList();
    }

    @Override
    public SpaceDto getDto(SpaceEntity entity) {
        log.info("SpaceDtoService produces Dto on the basis of a space entity.");
        return super.getDto(entity);
    }


}
