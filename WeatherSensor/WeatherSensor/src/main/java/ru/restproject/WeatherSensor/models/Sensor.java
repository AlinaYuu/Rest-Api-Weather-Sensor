package ru.restproject.WeatherSensor.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 3, max = 30)
    private String name;
    //  @OneToMany(mappedBy = "sensor")
    // private List<Measurement> measurementList;

    public Sensor() {
        //  measurementList = new ArrayList<>();
    }

    public Sensor(String name) {
        this.name = name;
        // measurementList = new ArrayList<>();
    }
}
