package com.javaremotero69.ex11_shop_app;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<GenericProduct> selectedProducts;

    public ShoppingCart() {
        this.selectedProducts = new ArrayList<>();
    }

    public void addToCart(GenericProduct product) {
        this.selectedProducts.add(product);
    }

    public void removeFromCart(GenericProduct product) {
        this.selectedProducts.remove(product);
    }

    public List<GenericProduct> getSelectedProducts() {
        return new ArrayList<>(this.selectedProducts);
    }
}
