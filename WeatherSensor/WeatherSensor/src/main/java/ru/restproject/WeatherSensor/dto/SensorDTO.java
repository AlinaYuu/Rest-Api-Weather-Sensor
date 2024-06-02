package ru.restproject.WeatherSensor.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SensorDTO {
    public SensorDTO() {
    }
    public SensorDTO(String name) {
        this.name = name;
    }

    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;
}
