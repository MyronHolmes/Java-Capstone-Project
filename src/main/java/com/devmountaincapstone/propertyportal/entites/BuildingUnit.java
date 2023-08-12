package com.devmountaincapstone.propertyportal.entites;

import com.devmountaincapstone.propertyportal.dtos.BuildingUnitDto;
import com.devmountaincapstone.propertyportal.enumerations.UnitType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Building_units")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_number")
    private Integer unitNumber;

    @Enumerated(value = EnumType.STRING)
    private UnitType unitType;

    @Column(name = "rent")
    private BigDecimal rent;

    @Column(name = "vacancy")
    private Boolean vacancy;

    @Column(name = "thumbnail")
    @Lob
    private Byte[] thumbnail;

    @ManyToOne
    @JsonBackReference
    private Building building;

    public BuildingUnit(BuildingUnitDto buildingUnitDto){
        if (buildingUnitDto.getUnitNumber() != null){
            this.unitNumber = buildingUnitDto.getUnitNumber();
        }
        if (buildingUnitDto.getUnitType() != null){
            this.unitType = buildingUnitDto.getUnitType();
        }
        if (buildingUnitDto.getRent() != null){
            this.rent = buildingUnitDto.getRent();
        }
        if (buildingUnitDto.getVacancy() != null){
            this.vacancy = buildingUnitDto.getVacancy();
        }
        if (buildingUnitDto.getThumbnail() != null){
            this.thumbnail = buildingUnitDto.getThumbnail();
        }
    }
}
