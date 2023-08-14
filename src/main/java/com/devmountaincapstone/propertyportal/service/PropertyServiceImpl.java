package com.devmountaincapstone.propertyportal.service;


import com.devmountaincapstone.propertyportal.dtos.PropertyDto;
import com.devmountaincapstone.propertyportal.entites.Landlord;
import com.devmountaincapstone.propertyportal.entites.Property;
import com.devmountaincapstone.propertyportal.repository.LandlordRepository;
import com.devmountaincapstone.propertyportal.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PropertyServiceImpl {
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private PropertyRepository propertyRepository;


    @Transactional
    public void addProperty(PropertyDto propertyDto, Long landlordId){
        Optional<Landlord> landlordOptional = landlordRepository.findById(landlordId);
        Property property = new Property(propertyDto);
        landlordOptional.ifPresent(property::setLandlord);
        propertyRepository.saveAndFlush(property);
    }

    @Transactional
    public void deletePropertyById(Long propertyId){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        propertyOptional.ifPresent(property -> propertyRepository.delete(property));
    }

    @Transactional
    public void updatePropertyById(PropertyDto propertyDto){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyDto.getId());
        propertyOptional.ifPresent(property -> {
            property.setPropertyName(propertyDto.getPropertyName());
        });
    }
}
