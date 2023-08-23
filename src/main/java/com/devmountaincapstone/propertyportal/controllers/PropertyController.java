package com.devmountaincapstone.propertyportal.controllers;


import com.devmountaincapstone.propertyportal.dtos.PropertyDto;
import com.devmountaincapstone.propertyportal.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;


    @GetMapping("landlord/{landlordId}")
    public List<PropertyDto> getPropertiesByLandlordId(@PathVariable Long landlordId){
        return propertyService.getAllPropertiesByLandlordId(landlordId);
    }

    @GetMapping("{propertyId}")
    public Optional<PropertyDto> getPropertyById(@PathVariable Long propertyId){
        return propertyService.getPropertyById(propertyId);
    }
    @PostMapping("/landlord/{landlordId}")
    public void addProperty(@RequestBody PropertyDto propertyDto, @PathVariable Long landlordId){
        propertyService.addProperty(propertyDto, landlordId);
    }

    @PutMapping("/{propertyId}")
    public void updatePropertyById(@RequestBody PropertyDto propertyDto, @PathVariable Long propertyId){
        propertyService.updatePropertyById(propertyDto);
    }

    @DeleteMapping("/{propertyId}")
    public void deletePropertyById(@PathVariable Long propertyId){
        propertyService.deletePropertyById(propertyId);
    }
}
