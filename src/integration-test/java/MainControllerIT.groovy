import com.fasterxml.jackson.databind.ObjectMapper
import models.ProductRQ
import models.ProductRS
import models.enums.ProductType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.client.RestTemplate
import pl.dawydiuk.FactoryInRestApi.FactoryInRestApiApplication
import pl.dawydiuk.FactoryInRestApi.config.ComponentConfig
import pl.dawydiuk.FactoryInRestApi.config.SecurityConfig
import pl.dawydiuk.FactoryInRestApi.controller.CustomControllerExceptionHandler
import pl.dawydiuk.FactoryInRestApi.controller.MainController
import spock.lang.Specification

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus
import static reactor.core.publisher.Mono.just

@SpringBootTest(classes = FactoryInRestApiApplication)
@ContextConfiguration(classes = [ComponentConfig.class, SecurityConfig.class])
class MainControllerIT extends Specification {

    @Autowired
    private MainController controller

    @Autowired
    private RestTemplate restTemplate

    @Autowired
    private CustomControllerExceptionHandler controllerExceptionHandler

    private MockRestServiceServer mockServer

    private WebTestClient webTestClient

    private ObjectMapper mapper

    def setup() {
        webTestClient = WebTestClient.bindToController(controller)
                .controllerAdvice(controllerExceptionHandler)
                .build()
        mockServer = MockRestServiceServer.createServer(restTemplate)
        mapper = new ObjectMapper()
    }

    def "should response success and status 201 when create product"() {
        given:
        def productRQ = ProductRQ.builder()
                .number(10)
                .type(ProductType.WASHBASIN)
                .build()

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8081/create")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().json(mapper.writeValueAsString(productRQ)))
                .andRespond(withStatus(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(new ProductRS()))
                )

        expect:
        webTestClient.post().uri("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifSx7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwibmFtZSI6InVzZXIiLCJpYXQiOjE1NjQ1OTg2ODYsImV4cCI6MTU2NDYwMDY4Nn0.8mPSkJ44MlTOlp4gvxDsZcCwVfffPDFgLEBDil3rDog")
                .body(just(productRQ), ProductRQ.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()


    }

    def "should response with correct error"() {
        given:
        def productRQ = ProductRQ.builder().build()

        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8081/create")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().json(mapper.writeValueAsString(productRQ)))
                .andRespond(withServerError())

        expect:
        webTestClient.post().uri("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifSx7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwibmFtZSI6InVzZXIiLCJpYXQiOjE1NjQ1OTg2ODYsImV4cCI6MTU2NDYwMDY4Nn0.8mPSkJ44MlTOlp4gvxDsZcCwVfffPDFgLEBDil3rDog")
                .body(just(productRQ), ProductRQ.class)
                .exchange()
                .expectStatus().is5xxServerError()
                .expectBody(String).isEqualTo("Error[code='1001',message='Service is not available']")
    }
}