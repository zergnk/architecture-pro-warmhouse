package ru.smarthome.telemetry.infrastructure.http.external;

import lombok.Data;
import ru.smarthome.telemetry.domain.TelemetryData;

import java.time.LocalDateTime;

@Data
class Sensor {
    private Integer id;
    private String status;
    private String unit;
    private LocalDateTime last_updated;
    private Double value;

    public TelemetryData convertToTelemetryData() {
        TelemetryData data = new TelemetryData();
        data.setDeviceId(id);
        data.setTime(last_updated);
        data.setStatus(status);
        data.setValue(value);

        return data;
    }

}
