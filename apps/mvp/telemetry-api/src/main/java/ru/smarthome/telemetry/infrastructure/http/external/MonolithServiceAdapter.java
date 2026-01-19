package ru.smarthome.telemetry.infrastructure.http.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.smarthome.telemetry.domain.TelemetryData;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class MonolithServiceAdapter {

    @Value("${monolith-api.url}")
    private String monolithApiUrl;

    private RestClient restClient;



    public MonolithServiceAdapter() {
        this.restClient = RestClient.create();;
    }

    public List<TelemetryData> getData() throws MonolithExchangeException {
        List<Sensor> response = restClient.get()
                    .uri(monolithApiUrl)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

        return response.stream()
                .map(Sensor::convertToTelemetryData)
                .toList();
    }
}
