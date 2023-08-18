package com.devmountaincapstone.propertyportal.service;


import com.devmountaincapstone.propertyportal.dtos.UnitDto;
import com.devmountaincapstone.propertyportal.entites.Building;
import com.devmountaincapstone.propertyportal.entites.Unit;
import com.devmountaincapstone.propertyportal.repository.BuildingRepository;
import com.devmountaincapstone.propertyportal.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private BuildingRepository buildingRepository;


    @Override
    @Transactional
    public void addUnit(UnitDto unitDto, Long buildingId){
        Optional<Building> buildingOptional = buildingRepository.findById(buildingId);
        Unit unit = new Unit(unitDto);
        buildingOptional.ifPresent(unit::setBuilding);
        unitRepository.saveAndFlush(unit);
    }

    @Override
    @Transactional
    public void deleteUnit(Long unitId){
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        unitOptional.ifPresent(unit -> {
            unitRepository.deleteById(unitId);
        });
    }

    @Override
    @Transactional
    public void updateUnitById(UnitDto unitDto){
        Optional<Unit> unitOptional = unitRepository.findById(unitDto.getId());
        unitOptional.ifPresent(unit -> {
            unit.setUnitNumber(unitDto.getUnitNumber());
            unit.setUnitType(unitDto.getUnitType());
            unit.setRent(unitDto.getRent());
            unit.setVacancy(unitDto.getVacancy());
            unit.setThumbnail(unitDto.getThumbnail());
            unitRepository.saveAndFlush(unit);
        });
    }


    @Override
    public List<UnitDto> getAllUnitsByBuildingId(Long buildingId){
        Optional<Building> buildingOptional = buildingRepository.findById(buildingId);
        if (buildingOptional.isPresent()){
            List<Unit> unitList = unitRepository.findByBuildingEquals(buildingOptional.get());
            return unitList.stream().map(unit -> new UnitDto(unit)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<UnitDto> getUnitById(Long unitId){
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()){
            return Optional.of(new UnitDto(unitOptional.get()));
        }
        return Optional.empty();
    }
}
