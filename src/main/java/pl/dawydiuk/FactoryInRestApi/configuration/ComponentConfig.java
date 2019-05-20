package pl.dawydiuk.FactoryInRestApi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.dawydiuk.FactoryInRestApi.service.MainService;

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
    public MainService mainService(RestTemplate restTemplate) {
        return new MainService(restTemplate);
    }
}
