package com.srasb.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "space")
public class SpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull (message = "The name field is empty.")
    @Size(min = 3, max = 20, message = "The name should be between 3 and 20 letters long.")
    @Column(name = "name",length=20, unique=true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull (message = "Type of space has not been chosen.")
    @Column(name = "type_of_space", nullable = false)
    private TypeOfSpace typeOfSpace;

    @Positive (message = "Price should not be lower than zero.")
    @NotNull (message = "The price field is empty.")
    @Column(name = "price", nullable = false)
    private int price;

    @OneToMany(mappedBy = "spaceEntity", cascade = CascadeType.REMOVE)
    List<ReservationEntity> reservationEntityList;

}
