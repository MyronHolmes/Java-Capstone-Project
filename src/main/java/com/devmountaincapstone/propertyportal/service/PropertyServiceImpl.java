package com.devmountaincapstone.propertyportal.service;


import com.devmountaincapstone.propertyportal.dtos.PropertyDto;
import com.devmountaincapstone.propertyportal.entites.Landlord;
import com.devmountaincapstone.propertyportal.entites.Property;
import com.devmountaincapstone.propertyportal.repository.LandlordRepository;
import com.devmountaincapstone.propertyportal.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private PropertyRepository propertyRepository;


    @Override
    @Transactional
    public void addProperty(PropertyDto propertyDto, Long landlordId){
        Optional<Landlord> landlordOptional = landlordRepository.findById(landlordId);
        Property property = new Property(propertyDto);
        landlordOptional.ifPresent(property::setLandlord);
        propertyRepository.saveAndFlush(property);
    }

    @Override
    @Transactional
    public void deletePropertyById(Long propertyId){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        propertyOptional.ifPresent(property -> propertyRepository.delete(property));
    }

    @Override
    @Transactional
    public void updatePropertyById(PropertyDto propertyDto){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyDto.getId());
        propertyOptional.ifPresent(property -> {
            property.setPropertyName(propertyDto.getPropertyName());
            propertyRepository.saveAndFlush(property);
        });
    }

    @Override
    public List<PropertyDto> getAllPropertiesByLandlordId(Long landlordId){
        Optional<Landlord> landlordOptional = landlordRepository.findById(landlordId);
        if (landlordOptional.isPresent()){
            List<Property> propertyList = propertyRepository.findByLandlordEquals(landlordOptional.get());
            return propertyList.stream().map(property -> new PropertyDto(property)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<PropertyDto> getPropertyById(Long propertyId){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isPresent()){
            return Optional.of(new PropertyDto(propertyOptional.get()));
        }
        return Optional.empty();
    }
}
