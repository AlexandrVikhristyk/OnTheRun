package com.gachigang.ontherun.payload.user.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessRequest {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Specify the country")
    private String country;

    @NotEmpty(message = "Specify the city ")
    private String city;

}
