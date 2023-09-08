package com.devmountaincapstone.propertyportal.controllers;


import com.devmountaincapstone.propertyportal.dtos.UnitDto;
import com.devmountaincapstone.propertyportal.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("units")
public class UnitController {
    @Autowired
    private UnitService unitService;


    @GetMapping("/building/{buildingId}")
    public List<UnitDto> getAllUnitsByBuildingId(@PathVariable Long buildingId){
        return unitService.getAllUnitsByBuildingId(buildingId);
    }

    @GetMapping("/{unitId}")
    public Optional<UnitDto> getUnitById(@PathVariable Long  unitId){
        return unitService.getUnitById(unitId);
    }

    @PostMapping("/building/{buildingId}")
    public void addUnit(@RequestBody UnitDto unitDto, @PathVariable Long buildingId){
        unitService.addUnit(unitDto, buildingId);
    }

    @PutMapping("/{unitId}")
    public void updateUnitById(@RequestBody UnitDto unitDto, @PathVariable Long unitId){
        unitService.updateUnitById(unitDto);
    }

    @DeleteMapping("/{unitId}")
    public void deleteUnitById(@PathVariable Long unitId){
        unitService.deleteUnit(unitId);
    }
}

