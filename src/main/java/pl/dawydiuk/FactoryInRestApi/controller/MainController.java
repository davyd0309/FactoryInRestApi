package pl.dawydiuk.FactoryInRestApi.controller;

import lombok.AllArgsConstructor;
import models.CreateProductRQ;
import models.ProductRS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.FactoryInRestApi.service.MainService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Konrad on 12.03.2019.
 */

@RestController
@AllArgsConstructor
public class MainController {

    private MainService mainService;

    @PostMapping(value = "/products", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductRS> createNewProducts(@RequestBody List<CreateProductRQ> createProductRQ) {
        return ResponseEntity.ok(mainService.createNewProducts(createProductRQ));

    }

    @GetMapping(value = "/products", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductRS> getAllProducts() {
        return ResponseEntity.ok(mainService.getAllProducts());

    }


}
