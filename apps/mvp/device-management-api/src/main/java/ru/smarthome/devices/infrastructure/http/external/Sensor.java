package ru.smarthome.devices.infrastructure.http.external;

import lombok.Data;
import ru.smarthome.devices.domain.Device;
import ru.smarthome.devices.domain.DeviceType;

@Data
class Sensor {
    // Монолит поддерживает только датчики температуры
    private final String type = "temperature";

    // Будет соответствовать (DeviceType.title + DeviceType.version)
    private final String name = "Реле и Автоматика (version: 1.2)";

    private Integer id;
    private String location;
    private String unit;

    static Sensor createFrom(Device device, DeviceType deviceType) {
        Sensor sensor = new Sensor();
        sensor.id = device.getId();
        sensor.location = device.getLocation();
        sensor.unit = device.getUnit();

        return sensor;
    }

}
