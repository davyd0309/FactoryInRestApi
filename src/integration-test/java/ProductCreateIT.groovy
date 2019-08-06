import com.fasterxml.jackson.databind.ObjectMapper
import models.ProductRQ
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import pl.dawydiuk.FactoryInRestApi.FactoryInRestApiApplication
import pl.dawydiuk.FactoryInRestApi.config.ComponentConfig
import pl.dawydiuk.FactoryInRestApi.config.SecurityConfig
import pl.dawydiuk.FactoryInRestApi.controller.MainController
import spock.lang.Specification

//@WebMvcTest(controllers = MainController.class)
@SpringBootTest(classes = FactoryInRestApiApplication)
@ContextConfiguration(classes = [ComponentConfig.class, SecurityConfig.class])
class ProductCreateIT extends Specification {

    @Autowired
    private MainController controller;

    private WebTestClient webTestClient;

    def setup() {
        webTestClient = WebTestClient.bindToController(controller).build()
    }

    def "should response success and status 201 when create product"() {
        expect:
        webTestClient.post().uri("/create")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated();


    }

    private static String asJsonString(final ProductRQ obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj)
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
