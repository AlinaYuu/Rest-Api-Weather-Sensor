package ru.restproject.WeatherSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.restproject.WeatherSensor.models.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
