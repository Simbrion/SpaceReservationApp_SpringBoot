package com.srasb.service.customerservice;

import com.srasb.model.dto.CustomerDto;
import com.srasb.model.entity.CustomerEntity;
import com.srasb.repository.CustomerRepository;
import com.srasb.repository.UserRepository;
import com.srasb.service.EntityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerEntityService implements EntityService<CustomerEntity, CustomerDto> {

    public final CustomerRepository customerRepository;
    public final UserRepository userRepository;

    public CustomerEntity getEntityFromDto(CustomerDto customerDto) {
        CustomerEntity newCustomerEntity = new CustomerEntity();
        newCustomerEntity.setName(customerDto.getName());
        return newCustomerEntity;
    }

    @Transactional
    public void addEntityBasedOn(CustomerDto customerDto) {
        customerRepository.save(getEntityFromDto(customerDto));
    }

    @Transactional
    public void deleteEntityById(int id) {
        customerRepository.deleteById(id);
        userRepository.deleteById(id);
    }

    public boolean isSaved(CustomerDto customerDto) {
        return customerRepository.findByName(customerDto.getName()).isPresent();
    }

    public CustomerEntity getEntityById(int id) {
        return customerRepository.findById(id).orElse(null);
    }

    public boolean findByName(String name) {
        return customerRepository.findByName(name).isPresent();
    }

    public Integer getEntityIdByName(String name) {
        return customerRepository.findByName(name)
                .map(CustomerEntity::getId)
                .orElse(null);
    }

    public void addCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

}
