package com.srasb.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationDto implements Dto {


    private int id;

    private String spaceName;

    private int spaceId;

    private String customerName;

    private int customerId;

    @NotNull(message = "Please choose the date.")
    @FutureOrPresent(message = "The date cannot be in the past.")
    private LocalDate date;

    @NotNull (message = "Please choose the time.")
    private LocalTime startTime;

    @NotNull (message = "Please choose the time.")
    private LocalTime endTime;


}
