package ru.warmhouse.temperature.sensors.infrastructure.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.warmhouse.temperature.sensors.application.TemperatureSensorService;
import ru.warmhouse.temperature.sensors.domain.TemperatureSensorData;

@RestController
@RequestMapping(path = "temperature", produces = MediaType.APPLICATION_JSON_VALUE)
public class TemperatureSensorController {

    private TemperatureSensorService temperatureSensorService;


    @Autowired
    public void setTemperatureSensorService(TemperatureSensorService temperatureSensorService) {
        this.temperatureSensorService = temperatureSensorService;
    }

    @GetMapping(path = "/{id}")
    public TemperatureSensorData getById(@PathVariable(name = "id") String sensorId) {
        return temperatureSensorService.getDataById(sensorId);
    }

    @GetMapping(path = "/")
    public TemperatureSensorData getByLocation(@RequestParam String location) {
        return temperatureSensorService.getDataByLocation(location);
    }
}
