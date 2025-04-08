package com.srasb.service.customerservice;

import com.srasb.model.dto.CustomerDto;
import com.srasb.model.entity.CustomerEntity;
import com.srasb.repository.CustomerRepository;
import com.srasb.service.DtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerDtoService implements DtoService<CustomerDto, CustomerEntity> {

    public final CustomerRepository customerRepository;

    public List<CustomerDto> getDtoList() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (CustomerEntity customerEntity : customerRepository.findAll()) {
            customerDtoList.add(this.getDto(customerEntity));
        }
        return customerDtoList;
    }

    public CustomerDto getDto(CustomerEntity customerEntity) {
       return  CustomerDto.builder()
                          .id(customerEntity.getId())
                          .name(customerEntity.getName())
                          .build();
    }


}
