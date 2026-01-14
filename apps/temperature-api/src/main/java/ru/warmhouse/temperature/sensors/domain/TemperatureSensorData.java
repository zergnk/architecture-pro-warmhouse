package ru.warmhouse.temperature.sensors.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class TemperatureSensorData {
    Double value;
    Status status;
    LocalDateTime lastUpdated;
}
