package ru.smarthome.devices.services;

import lombok.Data;
import ru.smarthome.devices.domain.Device;
import ru.smarthome.devices.domain.DeviceState;

@Data
public class DeviceDto {
    private Integer id;

    private Integer typeId;
    private Integer moduleId;
    private Integer houseId;
    private String location;
    private String serialNumber;
    private DeviceState state;
    private String unit;


    public static DeviceDto convertFromDevice(Device device) {
        DeviceDto dto = new DeviceDto();
        dto.setId(device.getId());
        dto.setTypeId(device.getTypeId());
        dto.setModuleId(device.getModuleId());
        dto.setHouseId(device.getHouseId());
        dto.setLocation(device.getLocation());
        dto.setSerialNumber(device.getSerialNumber());
        dto.setState(device.getState());
        dto.setUnit(device.getUnit());

        return dto;
    }

    public Device convertToDevice() {
        Device device = new Device();
        device.setId(getId());
        device.setTypeId(getTypeId());
        device.setModuleId(getModuleId());
        device.setHouseId(getHouseId());
        device.setLocation(getLocation());
        device.setSerialNumber(getSerialNumber());
        device.setState(getState());
        device.setUnit(getUnit());

        return device;
    }
}
