package com.devmountaincapstone.propertyportal.dtos;

import com.devmountaincapstone.propertyportal.entites.BuildingUnit;
import com.devmountaincapstone.propertyportal.enumerations.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingUnitDto implements Serializable {
    private Long id;
    private Integer unitNumber;
    private UnitType unitType;
    private BigDecimal rent;
    private Boolean vacancy;
    private Byte[] thumbnail;
    private BuildingDto buildingDto;

    public BuildingUnitDto(BuildingUnit buildingUnit){
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
        if (buildingUnit.getThumbnail() != null){
            this.thumbnail = buildingUnit.getThumbnail();
        }
    }
}
