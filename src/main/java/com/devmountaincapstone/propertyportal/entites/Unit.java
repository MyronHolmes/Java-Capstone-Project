package com.devmountaincapstone.propertyportal.entites;

import com.devmountaincapstone.propertyportal.dtos.UnitDto;
import com.devmountaincapstone.propertyportal.enumerations.UnitType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "Units")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unit_number")
    private Long unitNumber;

    @Enumerated(value = EnumType.STRING)
    private UnitType unitType;

    @Column(name = "rent")
    private BigDecimal rent;

    @Column(name = "vacancy")
    private Boolean vacancy;

    @ManyToOne
    @JsonBackReference
    private Building building;

    public Unit(UnitDto unitDto){
        if (unitDto.getUnitNumber() != null){
            this.unitNumber = unitDto.getUnitNumber();
        }
        if (unitDto.getUnitType() != null){
            this.unitType = unitDto.getUnitType();
        }
        if (unitDto.getRent() != null){
            this.rent = unitDto.getRent();
        }
        if (unitDto.getVacancy() != null){
            this.vacancy = unitDto.getVacancy();
        }
    }
}
