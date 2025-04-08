package com.srasb.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements Dto {

    private int id;

    @NotNull(message = "The name cannot be null.")
    @Length(min = 3, message = "The name should be at least 3 letters long.")
    @Length (max = 20, message = "The name should be not longer than 20 letters.")
    private String name;


}
