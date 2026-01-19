package ru.smarthome.telemetry.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.smarthome.telemetry.domain.TelemetryData;
import ru.smarthome.telemetry.domain.TelemetryDataRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class TelemetryDataService {
    private TelemetryDataRepository telemetryDataRepository;

    @Autowired
    public void setTelemetryDataRepository(TelemetryDataRepository telemetryDataRepository) {
        this.telemetryDataRepository = telemetryDataRepository;
    }


    public List<TelemetryDataDto> getAll(Integer deviceId, LocalTime startTime, LocalTime endTime) {
        LocalTime start = Objects.isNull(startTime) ? LocalTime.MIN : startTime;
        LocalTime end = Objects.isNull(endTime) ? LocalTime.MAX : endTime;

        return telemetryDataRepository.findAllByDeviceId(deviceId).stream()
                .map(TelemetryDataDto::convertFromTelemetryData)
                .toList();
    }

    public void create(List<TelemetryData> data) {
        telemetryDataRepository.saveAll(data);
    }
}
