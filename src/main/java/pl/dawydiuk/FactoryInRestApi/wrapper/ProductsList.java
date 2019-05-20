package pl.dawydiuk.FactoryInRestApi.wrapper;

import models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 12.03.2019.
 */

public class ProductsList {

    private List<Product> productsList = new ArrayList<>();

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
}
