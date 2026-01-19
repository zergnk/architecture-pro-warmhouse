package ru.smarthome.telemetry.services;

import lombok.Data;
import ru.smarthome.telemetry.domain.TelemetryData;

import java.time.LocalDateTime;

@Data
public class TelemetryDataDto {
    private Integer id;

    private Integer deviceId;
    private Double value;
    private String status;
    private LocalDateTime time;


    public static TelemetryDataDto convertFromTelemetryData(TelemetryData data) {
        TelemetryDataDto dto = new TelemetryDataDto();
        dto.setId(data.getId());
        dto.setDeviceId(data.getDeviceId());
        dto.setValue(data.getValue());
        dto.setStatus(data.getStatus());
        dto.setTime(data.getTime());

        return dto;
    }

    public TelemetryData convertToTelemetryData() {
        TelemetryData data = new TelemetryData();
        data.setId(getId());
        data.setDeviceId(getDeviceId());
        data.setValue(getValue());
        data.setStatus(getStatus());
        data.setTime(getTime());

        return data;
    }

}
