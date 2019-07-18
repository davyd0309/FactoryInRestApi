package pl.dawydiuk.FactoryInRestApi.config;

import models.ProductRQ;
import models.ProductRS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.dawydiuk.FactoryInRestApi.service.FoundryWrapperClient;
import pl.dawydiuk.FactoryInRestApi.service.FoundryWrapperClientAdapter;
import pl.dawydiuk.FactoryInRestApi.service.rest.FoundryAllProductsRestService;
import pl.dawydiuk.FactoryInRestApi.service.rest.FoundryCreateProductRestService;

import java.util.function.BiFunction;

/**
 * Created by Konrad on 06.04.2019.
 */

@Configuration
public class ComponentConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService(RestTemplate restTemplate) {
        return new FoundryCreateProductRestService(restTemplate);
    }

    @Bean
    public BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryAllProductsRestService(RestTemplate restTemplate) {
        return new FoundryAllProductsRestService(restTemplate);
    }

    @Bean
    public FoundryWrapperClient foundryWrapperClientAdapter(BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService,
                                                            BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryAllProductsRestService) {
        return new FoundryWrapperClientAdapter(foundryCreateProductRestService, foundryAllProductsRestService);
    }

}
