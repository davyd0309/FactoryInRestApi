package pl.dawydiuk.FactoryInRestApi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.CreateProductRQ;
import models.ProductRS;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Konrad on 12.03.2019.
 */
@AllArgsConstructor
@Slf4j
public class MainService {

    private RestTemplate restTemplate;

    private static final String PRODUCTS_URL = "http://localhost:8081/products";

    public ProductRS createNewProducts(final List<CreateProductRQ> createProductRQ) {
        return restTemplate.postForObject(PRODUCTS_URL,createProductRQ, ProductRS.class);
    }

    public ProductRS getAllProducts() {
        return restTemplate.getForObject(PRODUCTS_URL, ProductRS.class);
    }
}
