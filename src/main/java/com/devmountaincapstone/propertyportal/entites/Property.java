package com.devmountaincapstone.propertyportal.entites;

import com.devmountaincapstone.propertyportal.dtos.PropertyDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "property_name")
    private String propertyName;


    @Column(name = "projected_earnings")
    private BigDecimal projectedEarnings = new BigDecimal("0.00");


    @ManyToOne
    @JsonBackReference
    private Landlord landlord;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Building> buildingSet = new HashSet<>();

    public Property(PropertyDto propertyDto){
        if (propertyDto.getPropertyName() != null){
            this.propertyName = propertyDto.getPropertyName();
        }
        if (propertyDto.getProjectedEarnings() != null){
            this.projectedEarnings = propertyDto.getProjectedEarnings();
        }
    }
}
