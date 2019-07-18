package pl.dawydiuk.FactoryInRestApi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.ProductRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;

@AllArgsConstructor
@Slf4j
public class FoundryWrapperClientAdapter implements FoundryWrapperClient {

    private BiFunction<ProductRQ,String,ResponseEntity<ProductRS>> foundryCreateProductRestService;
    private BiFunction<ProductRQ,String,ResponseEntity<ProductRS>> foundryAllProductsRestService;

    @Override
    public ResponseEntity<ProductRS> invokeCreateProduct(ProductRQ productRQ, String token) {
        return foundryCreateProductRestService.apply(productRQ,token);
    }

    @Override
    public ResponseEntity<ProductRS> invokeAllProducts(ProductRQ productRQ,String token) {
        return foundryAllProductsRestService.apply(productRQ,token);
    }
}
