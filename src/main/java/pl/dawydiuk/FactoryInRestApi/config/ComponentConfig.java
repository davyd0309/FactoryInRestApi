package pl.dawydiuk.FactoryInRestApi.config;

import models.ProductCreateRQ;
import models.ProductRS;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.dawydiuk.FactoryInRestApi.handler.RestTemplateResponseErrorHandler;
import pl.dawydiuk.FactoryInRestApi.service.FoundryWrapperClient;
import pl.dawydiuk.FactoryInRestApi.service.FoundryWrapperClientAdapter;
import pl.dawydiuk.FactoryInRestApi.service.rest.FoundryAllProductsRestService;
import pl.dawydiuk.FactoryInRestApi.service.rest.FoundryCreateProductRestService;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by Konrad on 06.04.2019.
 */

@Configuration
public class ComponentConfig {

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        return new RestTemplateBuilder()
                .errorHandler(new RestTemplateResponseErrorHandler())
                .messageConverters(mappingJackson2HttpMessageConverter)
                .build();
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        return mappingJackson2HttpMessageConverter;
    }

    @Bean
    public BiFunction<ProductCreateRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService(RestTemplate restTemplate) {
        return new FoundryCreateProductRestService(restTemplate);
    }

    @Bean
    public Function<String, ResponseEntity<ProductRS>> foundryAllProductsRestService(RestTemplate restTemplate) {
        return new FoundryAllProductsRestService(restTemplate);
    }

    @Bean
    public FoundryWrapperClient foundryWrapperClientAdapter(BiFunction<ProductCreateRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService,
                                                            Function<String, ResponseEntity<ProductRS>> foundryAllProductsRestService) {
        return new FoundryWrapperClientAdapter(foundryCreateProductRestService, foundryAllProductsRestService);
    }
}
