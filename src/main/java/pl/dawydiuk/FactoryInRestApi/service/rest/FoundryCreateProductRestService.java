package pl.dawydiuk.FactoryInRestApi.service.rest;

import lombok.extern.slf4j.Slf4j;
import models.ProductRQ;
import models.ProductRS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.function.BiFunction;

/**
 * Created by Konrad on 12.03.2019.
 */
@Slf4j
public class FoundryCreateProductRestService implements BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> {

    @Value("${foundry.service.name}")
    private String serviceName;

    @Value("${foundry.service.endpoint.create}")
    private String foundryEndpoint;

    private final RestTemplate restTemplate;

    public FoundryCreateProductRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<ProductRS> apply(ProductRQ productRQ, String token){
        HttpEntity<ProductRQ> entity = new HttpEntity<>(productRQ, buildHeaders(token));
            return restTemplate.exchange(buildUrl(), HttpMethod.POST, entity, ProductRS.class);
    }

    private HttpHeaders buildHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", token);
        return headers;
    }

    private URI buildUrl() {
        try {
            return new URI("http://" + serviceName + foundryEndpoint); //TODO zmiana
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
