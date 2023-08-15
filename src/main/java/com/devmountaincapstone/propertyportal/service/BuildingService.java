package com.devmountaincapstone.propertyportal.service;

import com.devmountaincapstone.propertyportal.dtos.BuildingDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    @Transactional
    void addBuilding(BuildingDto buildingDto, Long propertyId);

    @Transactional
    void deleteBuilding(Long buildingId);

    @Transactional
    void updateBuilding(BuildingDto buildingDto);

    List<BuildingDto> getAllBuildingsByPropertyId(Long propertyId);

    Optional<BuildingDto> getBuildingById(Long buildingId);
}
