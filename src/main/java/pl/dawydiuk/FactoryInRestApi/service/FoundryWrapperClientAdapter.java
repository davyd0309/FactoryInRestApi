package pl.dawydiuk.FactoryInRestApi.service;

import lombok.extern.slf4j.Slf4j;
import models.ProductCreateRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class FoundryWrapperClientAdapter implements FoundryWrapperClient {

    private final BiFunction<ProductCreateRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService;
    private final Function<String, ResponseEntity<ProductRS>> foundryAllProductsRestService;

    public FoundryWrapperClientAdapter(BiFunction<ProductCreateRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService, Function<String, ResponseEntity<ProductRS>> foundryAllProductsRestService) {
        this.foundryCreateProductRestService = foundryCreateProductRestService;
        this.foundryAllProductsRestService = foundryAllProductsRestService;
    }

    @Override
    public ResponseEntity<ProductRS> invokeCreateProduct(ProductCreateRQ productCreateRQ, String token) {
        return foundryCreateProductRestService.apply(productCreateRQ, token);
    }

    @Override
    public ResponseEntity<ProductRS> invokeAllProducts(String token) {
        return foundryAllProductsRestService.apply(token);
    }
}
