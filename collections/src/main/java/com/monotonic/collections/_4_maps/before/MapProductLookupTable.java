package com.monotonic.collections._4_maps.before;

import java.util.HashMap;
import java.util.Map;

public class MapProductLookupTable implements ProductLookupTable {
    private final Map<Integer, Product> idToProduct = new HashMap<>();

    @Override
    public void addProduct(final Product productToAdd) {
        var id = productToAdd.getId();
        if (idToProduct.putIfAbsent(id, productToAdd) != null) {
            throw new IllegalArgumentException(
                    "Unable to add product - duplicate id for" + productToAdd);
        }
    }

    @Override
    public Product lookupById(final int id) {
        return idToProduct.get(id);
    }

    @Override
    public void clear() {
        idToProduct.clear();
    }
}
