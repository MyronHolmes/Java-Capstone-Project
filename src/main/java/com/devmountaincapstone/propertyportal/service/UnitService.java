package com.devmountaincapstone.propertyportal.service;

import com.devmountaincapstone.propertyportal.dtos.UnitDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UnitService {
    @Transactional
    void addUnit(UnitDto unitDto, Long buildingId);

    void deleteUnit(Long unitId);

    @Transactional
    void updateUnitById(UnitDto unitDto);

    List<UnitDto> getAllUnitsByBuildingId(Long buildingId);

    Optional<UnitDto> getUnitById(Long unitId);
}
