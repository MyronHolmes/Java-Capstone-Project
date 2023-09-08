package com.devmountaincapstone.propertyportal.dtos;

import com.devmountaincapstone.propertyportal.entites.Unit;
import com.devmountaincapstone.propertyportal.enumerations.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitDto implements Serializable {
    private Long id;
    private Long unitNumber;
    private UnitType unitType;
    private BigDecimal rent;
    private Boolean vacancy;
    private BuildingDto buildingDto;

    public UnitDto(Unit buildingUnit){
        if (buildingUnit.getId() != null){
            this.id = buildingUnit.getId();
        }
        if (buildingUnit.getUnitNumber() != null){
            this.unitNumber = buildingUnit.getUnitNumber();
        }
        if (buildingUnit.getUnitType() != null){
            this.unitType = buildingUnit.getUnitType();
        }
        if (buildingUnit.getRent() != null){
            this.rent = buildingUnit.getRent();
        }
        if (buildingUnit.getVacancy() != null){
            this.vacancy = buildingUnit.getVacancy();
        }
    }
}
