package com.devmountaincapstone.propertyportal.dtos;

import com.devmountaincapstone.propertyportal.entites.Building;
import com.devmountaincapstone.propertyportal.entites.Landlord;
import com.devmountaincapstone.propertyportal.entites.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto implements Serializable {
    private Long id;
    private String propertyName;
    private BigDecimal projectedEarnings;
    private LandlordDto landlordDto;
    private Set<BuildingDto> buildingDtoSetSet = new HashSet<>();


    public PropertyDto(Property property){
        if (property.getId() != null){
            this.id = property.getId();
        }
        if (property.getPropertyName() != null){
            this.propertyName = property.getPropertyName();
        }
        if (property.getProjectedEarnings() != null){
            this.projectedEarnings = property.getProjectedEarnings();
        }

    }



}
