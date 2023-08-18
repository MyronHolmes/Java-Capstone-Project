package com.devmountaincapstone.propertyportal.service;


import com.devmountaincapstone.propertyportal.dtos.BuildingDto;
import com.devmountaincapstone.propertyportal.dtos.PropertyDto;
import com.devmountaincapstone.propertyportal.entites.Building;
import com.devmountaincapstone.propertyportal.entites.Landlord;
import com.devmountaincapstone.propertyportal.entites.Property;
import com.devmountaincapstone.propertyportal.repository.BuildingRepository;
import com.devmountaincapstone.propertyportal.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private PropertyRepository propertyRepository;


    @Override
    @Transactional
    public void addBuilding(BuildingDto buildingDto, Long propertyId){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        Building building = new Building(buildingDto);
        propertyOptional.ifPresent(building::setProperty);
        buildingRepository.saveAndFlush(building);
    }

    @Override
    @Transactional
    public void deleteBuilding(Long buildingId){
        Optional<Building> buildingOptional = buildingRepository.findById(buildingId);
        buildingOptional.ifPresent(building -> buildingRepository.delete(building));
    }

    @Override
    @Transactional
    public void updateBuildingById(BuildingDto buildingDto){
        Optional<Building> buildingOptional = buildingRepository.findById(buildingDto.getId());
        buildingOptional.ifPresent(building -> {
            building.setBuildingNumber(buildingDto.getBuildingNumber());
            buildingRepository.saveAndFlush(building);
        });
    }

    @Override
    public List<BuildingDto> getAllBuildingsByPropertyId(Long propertyId){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if (propertyOptional.isPresent()){
            List<Building> buildingList = buildingRepository.findByPropertyEquals(propertyOptional.get());
            return buildingList.stream().map(building -> new BuildingDto(building)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }


    @Override
    public Optional<BuildingDto> getBuildingById(Long buildingId){
        Optional<Building> buildingOptional = buildingRepository.findById(buildingId);
        if (buildingOptional.isPresent()){
            return Optional.of(new BuildingDto(buildingOptional.get()));
        }
        return Optional.empty();
    }

}
