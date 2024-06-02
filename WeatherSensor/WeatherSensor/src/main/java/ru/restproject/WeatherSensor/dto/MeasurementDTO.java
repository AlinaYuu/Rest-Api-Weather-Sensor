package ru.restproject.WeatherSensor.dto;

import lombok.Getter;
import lombok.Setter;
import ru.restproject.WeatherSensor.models.Sensor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
public class MeasurementDTO {
    private double value;
    @NotNull
    private boolean raining;
    @NotNull
    private SensorDTO sensor;
}
