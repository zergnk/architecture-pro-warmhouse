package ru.smarthome.devices.infrastructure.http.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.smarthome.devices.domain.Device;
import ru.smarthome.devices.domain.DeviceType;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class MonolithServiceAdapter {

    @Value("${monolith-api.url}")
    private String monolithApiUrl;

    private RestClient restClient;



    public MonolithServiceAdapter() {
        this.restClient = RestClient.create();;
    }

    public void createDevice(Device device, DeviceType deviceType) throws MonolithExchangeException {
        Sensor sensor = Sensor.createFrom(device, deviceType);
        ResponseEntity<Void> response = restClient.post()
                    .uri(monolithApiUrl)
                    .contentType(APPLICATION_JSON)
                    .body(sensor)
                    .retrieve()
                    .toBodilessEntity();

            if (response.getStatusCode().isError())
                throw new MonolithExchangeException();
    }

    public void updateDevice(Device device, DeviceType deviceType) throws MonolithExchangeException {
        Sensor sensor = Sensor.createFrom(device, deviceType);

        ResponseEntity<Void> response = restClient.put()
                .uri(monolithApiUrl + "/" + device.getId())
                .contentType(APPLICATION_JSON)
                .body(sensor)
                .retrieve()
                .toBodilessEntity();

        if (response.getStatusCode().isError())
            throw new MonolithExchangeException();
    }

    public void deleteDevice(Integer id) throws MonolithExchangeException {
        ResponseEntity<Void> response = restClient.delete()
                .uri(monolithApiUrl + "/" + id)
                .retrieve()
                .toBodilessEntity();

        if (response.getStatusCode().isError())
            throw new MonolithExchangeException();
    }
}
