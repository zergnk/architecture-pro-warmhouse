package ru.smarthome.devices.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer typeId;
    private Integer moduleId;
    private Integer houseId;
    private String location;
    private String serialNumber;
    private DeviceState state;
    private String unit;
}

