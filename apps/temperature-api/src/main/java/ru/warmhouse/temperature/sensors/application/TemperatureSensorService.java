package ru.warmhouse.temperature.sensors.application;

import org.springframework.stereotype.Service;
import ru.warmhouse.temperature.sensors.domain.Status;
import ru.warmhouse.temperature.sensors.domain.TemperatureSensorData;

import java.time.LocalDateTime;
import java.util.Random;


@Service
public class TemperatureSensorService {
    public TemperatureSensorData getDataById(String sensorId) {
        return new TemperatureSensorData(generateTemperature(), Status.active, LocalDateTime.now());
    }

    public TemperatureSensorData getDataByLocation(String location) {
        return new TemperatureSensorData(generateTemperature(), Status.active, LocalDateTime.now());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Helper methods /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private double generateTemperature() {
        double minTemperature = -10.0;
        double maxTemperature = 35.0;
        double range = maxTemperature - minTemperature;
        double value = minTemperature + range * new Random().nextDouble();

        double scale = Math.pow(10, 2);

        // return rounded generated value
        return Math.ceil(value * scale) / scale;
    }

}
