package pl.dawydiuk.FactoryInRestApi.controller;

import lombok.extern.slf4j.Slf4j;
import models.ProductCreateRQ;
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
            @RequestBody ProductCreateRQ productCreateRQ) {
        return foundryWrapperClientAdapter.invokeCreateProduct(productCreateRQ, token);
    }

    @PostMapping(value = "/all")
    public ResponseEntity<ProductRS> getAllProducts(
            @RequestHeader("Authorization") String token) {
        return foundryWrapperClientAdapter.invokeAllProducts(token);
    }


    @GetMapping
    public String testEnd(){
        return "SUCCES RESPONSE";
    }

}
