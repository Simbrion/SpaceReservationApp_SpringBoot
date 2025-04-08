package com.srasb.model.dto;

import com.srasb.model.entity.TypeOfSpace;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpaceDto implements Dto {

    private int id;

    @NotNull(message = "The name field is empty.")
    @Size(min = 3, max = 20, message = "The name should be between 3 and 20 letters long.")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull (message = "Type of space has not been chosen.")
    private TypeOfSpace typeOfSpace;

    @Positive(message = "Price should not be lower than zero.")
    @NotNull (message = "The price field is empty.")
    private int price;

}
