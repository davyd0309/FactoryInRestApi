package pl.dawydiuk.FactoryInRestApi.service;

import lombok.AllArgsConstructor;
import models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.dawydiuk.FactoryInRestApi.wrapper.ProductsList;

import java.util.List;

/**
 * Created by Judith on 12.03.2019.
 */
@Service
@AllArgsConstructor
public class MainService {

    private RestTemplate restTemplate;

    private static final String CREATE_NEW_PRODUCTS_URL = "http://localhost:8083//create/";


    public List<Product> createNewProducts(final int number) {
        return restTemplate.getForObject(CREATE_NEW_PRODUCTS_URL + number, ProductsList.class).getProductsList();
    }

}
