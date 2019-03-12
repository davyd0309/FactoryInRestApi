package pl.dawydiuk.FactoryInRestApi.wrapper;

import lombok.AllArgsConstructor;
import models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Judith on 12.03.2019.
 */

@AllArgsConstructor
public class ProductsList {

    private List<Product> productsList = new ArrayList<>();

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
}
