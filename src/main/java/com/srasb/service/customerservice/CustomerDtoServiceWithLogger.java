package com.srasb.service.customerservice;

import com.srasb.model.dto.CustomerDto;
import com.srasb.model.entity.CustomerEntity;
import com.srasb.service.DtoServiceWithLogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
@Log4j2
public class CustomerDtoServiceWithLogger extends DtoServiceWithLogger<CustomerDto, CustomerEntity> {

    public CustomerDtoServiceWithLogger(CustomerDtoService dtoService) {
        super(dtoService);
    }

    @Override
    public List<CustomerDto> getDtoList() {
        log.info("CustomerDtoService produces Dto list.");
        return super.getDtoList();
    }

    @Override
    public CustomerDto getDto(CustomerEntity entity) {
        log.info("CustomerDtoService produces Dto on the basis of a customer entity.");
        return super.getDto(entity);
    }
}
