package pl.dawydiuk.FactoryInRestApi.service;

import lombok.extern.slf4j.Slf4j;
import models.ProductRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;
@Slf4j
public class FoundryWrapperClientAdapter implements FoundryWrapperClient {

    private final BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService;
    private final BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryAllProductsRestService;

    public FoundryWrapperClientAdapter(BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryCreateProductRestService, BiFunction<ProductRQ, String, ResponseEntity<ProductRS>> foundryAllProductsRestService) {
        this.foundryCreateProductRestService = foundryCreateProductRestService;
        this.foundryAllProductsRestService = foundryAllProductsRestService;
    }

    @Override
    public ResponseEntity<ProductRS> invokeCreateProduct(ProductRQ productRQ, String token) {
        return foundryCreateProductRestService.apply(productRQ, token);
    }

    @Override
    public ResponseEntity<ProductRS> invokeAllProducts(ProductRQ productRQ, String token) {
        return foundryAllProductsRestService.apply(productRQ, token);
    }
}
