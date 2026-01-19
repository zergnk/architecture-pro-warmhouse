package ru.smarthome.devices.infrastructure.http.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.smarthome.devices.infrastructure.http.external.MonolithExchangeException;
import ru.smarthome.devices.services.DeviceDto;
import ru.smarthome.devices.services.DeviceService;
import ru.smarthome.devices.services.EntityNotFoundException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private DeviceService deviceService;

    @Autowired
    public void setDeviceService(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAll() {
        return deviceService.getAll();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public DeviceDto get(@PathVariable Integer id) throws Exception {
        return deviceService.get(id);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public DeviceDto create(@RequestBody DeviceDto dto) throws Exception {
        return deviceService.create(dto);
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public DeviceDto update(@PathVariable Integer id, @RequestBody DeviceDto dto) throws Exception {
        dto.setId(id);
        return  deviceService.update(dto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        deviceService.delete(id);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Exception handling /////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleNotFoundException() {

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MonolithExchangeException.class)
    public void handleExchangeException() {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void handleException() {

    }

}
