package ru.smarthome.telemetry.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TelemetryDataRepository extends JpaRepository<TelemetryData, Integer> {

    Collection<TelemetryData> findAllByDeviceId(Integer deviceId);
}
