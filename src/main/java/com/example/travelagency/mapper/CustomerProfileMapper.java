package com.example.travelagency.mapper;

import com.example.travelagency.dto.CustomerProfileDto;
import com.example.travelagency.entity.CustomerProfile;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface CustomerProfileMapper {

    @Mapping(target = "customer", ignore = true)
    CustomerProfileDto customerProfileToCustomerProfileDto(CustomerProfile customerProfile);

    @InheritInverseConfiguration(name = "customerProfileToCustomerProfileDto")
    CustomerProfile customerProfileDtoToCustomerProfile (CustomerProfileDto customerProfileDto);
}
