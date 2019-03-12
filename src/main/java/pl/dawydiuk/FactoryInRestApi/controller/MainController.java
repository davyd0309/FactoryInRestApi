package pl.dawydiuk.FactoryInRestApi.controller;

import lombok.AllArgsConstructor;
import models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.dawydiuk.FactoryInRestApi.service.MainService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Judith on 12.03.2019.
 */

@RestController
@AllArgsConstructor
public class MainController {

    private MainService mainService;

    @GetMapping(value = "/product/{number}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> createNewProducts(@PathVariable int number) {

        return ResponseEntity.ok(mainService.createNewProducts(number));

    }

}
