package com.devmountaincapstone.propertyportal.dtos;

import com.devmountaincapstone.propertyportal.entites.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto implements Serializable {
    private Long id;
    private Integer buildingNumber;
    private PropertyDto propertyDto;
    private Set<UnitDto> unitDtoSet = new HashSet<>();


    public BuildingDto(Building building){
        if (building.getId() != null){
            this.id = building.getId();
        }
        if (building.getBuildingNumber() != null){
            this.buildingNumber = building.getBuildingNumber();
        }
    }
}
