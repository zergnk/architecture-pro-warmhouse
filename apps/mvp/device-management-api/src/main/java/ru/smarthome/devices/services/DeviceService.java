package ru.smarthome.devices.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.smarthome.devices.domain.Device;
import ru.smarthome.devices.domain.DeviceRepository;
import ru.smarthome.devices.domain.DeviceType;
import ru.smarthome.devices.domain.DeviceTypeRepository;
import ru.smarthome.devices.infrastructure.http.external.MonolithExchangeException;
import ru.smarthome.devices.infrastructure.http.external.MonolithServiceAdapter;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class DeviceService {

    private DeviceRepository deviceRepository;
    private DeviceTypeRepository deviceTypeRepository;
    private MonolithServiceAdapter monolithServiceAdapter;


    @Autowired
    public void setDeviceRepository(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Autowired
    public void setDeviceTypeRepository(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @Autowired
    public void setMonolithServiceAdapter(MonolithServiceAdapter monolithServiceAdapter) {
        this.monolithServiceAdapter = monolithServiceAdapter;
    }


    public List<DeviceDto> getAll() {
        return deviceRepository.findAll().stream()
                .map(DeviceDto::convertFromDevice)
                .toList();
    }

    public DeviceDto get(Integer id) throws EntityNotFoundException {
        Device device = deviceRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return DeviceDto.convertFromDevice(device);
    }

    public DeviceDto create(DeviceDto dto) throws MonolithExchangeException {
        Device device = dto.convertToDevice();
        Device persistedDevice = deviceRepository.save(device);
        DeviceType deviceType = getDeviceTypeForDevice(persistedDevice);
        monolithServiceAdapter.createDevice(persistedDevice, deviceType);

        return DeviceDto.convertFromDevice(persistedDevice);
    }

    public DeviceDto update(DeviceDto dto) throws EntityNotFoundException, MonolithExchangeException {
        Device device = deviceRepository.findById(dto.getId())
                .orElseThrow(EntityNotFoundException::new);

        // update device

        if (Objects.nonNull(dto.getTypeId())) {
            device.setHouseId(dto.getTypeId());
        }

        if (Objects.nonNull(dto.getHouseId())) {
            device.setHouseId(dto.getHouseId());
        }

        if (Objects.nonNull(dto.getModuleId())) {
            device.setModuleId(dto.getModuleId());
        }

        if (StringUtils.hasText(dto.getLocation())) {
            device.setLocation(dto.getLocation());
        }

        if (StringUtils.hasText(dto.getUnit())) {
            device.setUnit(dto.getUnit());
        }

        if (StringUtils.hasText(dto.getSerialNumber())) {
            device.setSerialNumber(dto.getSerialNumber());
        }

        if (Objects.nonNull(dto.getState())) {
            device.setState(dto.getState());
        }

        Device persistedDevice = deviceRepository.save(device);
        DeviceType deviceType = getDeviceTypeForDevice(persistedDevice);
        monolithServiceAdapter.updateDevice(persistedDevice, deviceType);

        return DeviceDto.convertFromDevice(persistedDevice);
    }

    public void delete(Integer id) throws MonolithExchangeException {
        deviceRepository.deleteById(id);
        monolithServiceAdapter.deleteDevice(id);
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Helper Methods //////////////////////////////////////////////////////////////////////////////////////////////////////

    public DeviceType getDeviceTypeForDevice(Device device) {
        // deviceTypeRepository.findById(device.getTypeId())
        return new DeviceType();
    }
}
