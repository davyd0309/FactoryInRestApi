package pl.dawydiuk.FactoryInRestApi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.ProductRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.FactoryInRestApi.service.FoundryWrapperClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Konrad on 12.03.2019.
 */
@RestController
@AllArgsConstructor
@Slf4j
public class MainController {

    private final FoundryWrapperClient foundryWrapperClientAdapter;

    @PostMapping(value = "/create", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductRS> createNewProducts(
            @RequestHeader("Authorization") String token,
            @RequestBody ProductRQ productRQ) {
        return foundryWrapperClientAdapter.invokeCreateProduct(productRQ,token);
    }

    @PostMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductRS> getAllProducts(
            @RequestHeader("Authorization") String token,
            @RequestBody ProductRQ productRQ) {
        return foundryWrapperClientAdapter.invokeAllProducts(productRQ,token);
    }

}
