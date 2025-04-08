package com.srasb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull (message = "The name cannot be null.")
    @Size(min = 3, max = 20, message = "The name should be between 3 and 20 letters long.")
    @Column(name = "name",length=20, unique=true, nullable=false)
    private String name;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    List<ReservationEntity> reservationEntityList;

}
