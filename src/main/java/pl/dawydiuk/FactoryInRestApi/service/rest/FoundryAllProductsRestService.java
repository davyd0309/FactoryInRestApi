package pl.dawydiuk.FactoryInRestApi.service.rest;

import lombok.extern.slf4j.Slf4j;
import models.ProductRS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.function.Function;

/**
 * Created by Konrad on 12.03.2019.
 */
@Slf4j
public class FoundryAllProductsRestService implements Function<String, ResponseEntity<ProductRS>> {

    @Value("${foundry.service.name}")
    private String serviceName;

    @Value("${foundry.service.endpoint.all}")
    private String foundryEndpoint;

    private final RestTemplate restTemplate;

    public FoundryAllProductsRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<ProductRS> apply(String token) {
        return restTemplate.exchange(buildUrl(), HttpMethod.POST, new HttpEntity<>(buildHeaders(token)), ProductRS.class);
    }

    private HttpHeaders buildHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", token);
        return headers;
    }

    private URI buildUrl() {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(serviceName)
                .path(foundryEndpoint).build().toUri();
    }

}
