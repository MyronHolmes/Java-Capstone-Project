package com.devmountaincapstone.propertyportal.dtos;

import com.devmountaincapstone.propertyportal.entites.Building;
import com.devmountaincapstone.propertyportal.entites.BuildingUnit;
import com.devmountaincapstone.propertyportal.entites.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

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
    private Set<BuildingUnitDto> buildingUnitDtoSet = new HashSet<>();


    public BuildingDto(Building building){
        if (building.getId() != null){
            this.id = building.getId();
        }
        if (building.getBuildingNumber() != null){
            this.buildingNumber = building.getBuildingNumber();
        }
    }
}
