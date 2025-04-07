package com.monotonic.collections._4_maps.before;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ViewsOverMaps
{
    public static void main(String[] args)
    {
        var defaultProduct = new Product(-1, "Whatever the customer wants", 100);

        var idToProduct = new HashMap<Integer, Product>();
        idToProduct.put(1, ProductFixtures.door);
        idToProduct.put(2, ProductFixtures.floorPanel);
        idToProduct.put(3, ProductFixtures.window);

        System.out.println(idToProduct);

      idToProduct.replaceAll((key, oldProduct) -> new Product(
              oldProduct.getId(),
              oldProduct.getName(),
              oldProduct.getWeight() + 10));

      System.out.println(idToProduct);


    }
}
