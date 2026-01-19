package ru.smarthome.telemetry.infrastructure.http.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smarthome.telemetry.services.TelemetryDataDto;
import ru.smarthome.telemetry.services.TelemetryDataService;

import java.time.LocalTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/telemetry-data")
public class TelemetryDataController {

    private TelemetryDataService telemetryDataService;

    @Autowired
    public void setTelemetryDataService(TelemetryDataService telemetryDataService) {
        this.telemetryDataService = telemetryDataService;
    }


    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<TelemetryDataDto> getAll(
            @RequestParam(name = "device-id") Integer deviceId,
            @RequestParam(name = "start-time", required = false) LocalTime startTime,
            @RequestParam(name = "end-time", required = false) LocalTime endTime
    ) {
        return telemetryDataService.getAll(deviceId, startTime, endTime);
    }

}
