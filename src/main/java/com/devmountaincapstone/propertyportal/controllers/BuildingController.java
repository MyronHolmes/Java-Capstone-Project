package com.devmountaincapstone.propertyportal.controllers;


import com.devmountaincapstone.propertyportal.dtos.BuildingDto;
import com.devmountaincapstone.propertyportal.service.BuildingService;
import com.devmountaincapstone.propertyportal.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping("/property/{propertyId}")
    public List<BuildingDto> getBuildingsByPropertyId(@PathVariable Long propertyId){
        return buildingService.getAllBuildingsByPropertyId(propertyId);
    }

    @GetMapping("/{buildingId}")
    public Optional<BuildingDto> getBuildingById(@PathVariable Long buildingId){
        return buildingService.getBuildingById(buildingId);
    }
    @PostMapping("/property/{propertyId}")
    public void addBuilding(@RequestBody BuildingDto buildingDto, @PathVariable Long propertyId){
        buildingService.addBuilding(buildingDto, propertyId);
    }

    @PutMapping("/{buildingId}")
    public void updateBuilding(@RequestBody BuildingDto buildingDto, @PathVariable Long buildingId){
        buildingService.updateBuildingById(buildingDto);
    }

    @DeleteMapping("/{buildingId}")
    public void deleteBuildingById(@PathVariable Long buildingId){
        buildingService.deleteBuilding(buildingId);
    }
}
