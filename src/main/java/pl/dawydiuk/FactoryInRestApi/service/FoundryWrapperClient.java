package pl.dawydiuk.FactoryInRestApi.service;

import models.ProductCreateRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;

public interface FoundryWrapperClient {
    ResponseEntity<ProductRS> invokeCreateProduct(ProductCreateRQ productCreateRQ, String token);
    ResponseEntity<ProductRS> invokeAllProducts(String token);
}
