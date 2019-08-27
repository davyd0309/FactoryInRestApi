package pl.dawydiuk.FactoryInRestApi.controller;

import lombok.extern.slf4j.Slf4j;
import models.ProductRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dawydiuk.FactoryInRestApi.service.FoundryWrapperClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by Konrad on 12.03.2019.
 */
@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class MainController {

    private final FoundryWrapperClient foundryWrapperClientAdapter;

    public MainController(FoundryWrapperClient foundryWrapperClientAdapter) {
        this.foundryWrapperClientAdapter = foundryWrapperClientAdapter;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ProductRS> createNewProducts(
            @RequestHeader("Authorization") String token,
            @RequestBody ProductRQ productRQ) {
        return foundryWrapperClientAdapter.invokeCreateProduct(productRQ, token);
    }

    @PostMapping(value = "/all")
    public ResponseEntity<ProductRS> getAllProducts(
            @RequestHeader("Authorization") String token,
            @RequestBody ProductRQ productRQ) {
        return foundryWrapperClientAdapter.invokeAllProducts(productRQ, token);
    }

}
