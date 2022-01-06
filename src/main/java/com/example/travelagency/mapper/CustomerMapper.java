package com.example.travelagency.mapper;

import com.example.travelagency.dto.CustomerDto;
import com.example.travelagency.entity.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerProfileMapper.class, BookingMapper.class})
public interface CustomerMapper {

    @Mapping(target = "bookings.customer", ignore = true)
    CustomerDto customerToCustomerDto(Customer customer);

    @InheritInverseConfiguration(name = "customerToCustomerDto")
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
