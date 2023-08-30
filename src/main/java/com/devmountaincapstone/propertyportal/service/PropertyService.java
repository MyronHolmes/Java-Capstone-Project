package com.devmountaincapstone.propertyportal.service;

import com.devmountaincapstone.propertyportal.dtos.PropertyDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    @Transactional
    void addProperty(PropertyDto propertyDto, Long landlordId);

    @Transactional
    void deletePropertyById(Long propertyId);

    @Transactional
    void updatePropertyById(PropertyDto propertyDto);

    List<PropertyDto> getAllPropertiesByLandlordId(Long landlordId);

    Optional<PropertyDto> getPropertyById(Long propertyId);

    List<String> propertyBuildings(Long propertyId);
}
