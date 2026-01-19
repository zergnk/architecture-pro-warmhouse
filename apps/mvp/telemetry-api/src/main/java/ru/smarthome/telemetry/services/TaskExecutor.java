package ru.smarthome.telemetry.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.smarthome.telemetry.domain.TelemetryData;
import ru.smarthome.telemetry.infrastructure.http.external.MonolithExchangeException;
import ru.smarthome.telemetry.infrastructure.http.external.MonolithServiceAdapter;

import java.util.List;

@Component
public class TaskExecutor {

    @Autowired
    private MonolithServiceAdapter monolithServiceAdapter;

    @Autowired
    private TelemetryDataService telemetryDataService;


    @Scheduled(fixedDelay = 5000)
    public void execute() throws MonolithExchangeException {
        List<TelemetryData> data = monolithServiceAdapter.getData();
        telemetryDataService.create(data);
    }
}
