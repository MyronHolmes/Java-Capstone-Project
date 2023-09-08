package com.devmountaincapstone.propertyportal.entites;

import com.devmountaincapstone.propertyportal.dtos.BuildingDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Buildings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "building_number")
    private Long buildingNumber;

    @ManyToOne
    @JsonBackReference
    private Property property;

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private Set<Unit> unitSet = new HashSet<>();

    public Building(BuildingDto buildingDto){
        if (buildingDto.getBuildingNumber() != null){
            this.buildingNumber = buildingDto.getBuildingNumber();
        }
    }


}
