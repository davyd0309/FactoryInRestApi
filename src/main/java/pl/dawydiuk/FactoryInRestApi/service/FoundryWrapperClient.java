package pl.dawydiuk.FactoryInRestApi.service;

import models.ProductRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;

public interface FoundryWrapperClient {
    ResponseEntity<ProductRS> invokeCreateProduct(ProductRQ productRQ,String token);
    ResponseEntity<ProductRS> invokeAllProducts(ProductRQ productRQ, String token);
}
