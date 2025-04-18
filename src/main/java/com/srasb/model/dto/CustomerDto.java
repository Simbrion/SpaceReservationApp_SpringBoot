package com.srasb.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements Dto {

    private int id;

    @NotNull(message = "The name cannot be null.")
    @Size(min = 3, max = 20, message = "The name should be between 3 and 20 letters long.")
    private String name;

    private String password;


}
