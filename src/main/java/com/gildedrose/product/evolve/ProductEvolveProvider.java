package com.gildedrose.product.evolve;

import com.gildedrose.ProductType;
import com.gildedrose.exceptions.GiledRoseProductException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductEvolveProvider {
    private static Map<ProductType, ProductEvolve> EVOLVES = new HashMap<>();

    static {
        EVOLVES.put(ProductType.STANDARD, new StandardProductEvolve());
        EVOLVES.put(ProductType.AGED_BRIE, new AgedBrieProductEvolve());
        EVOLVES.put(ProductType.BACKSTAGE, new BackstageProductEvolve());
        EVOLVES.put(ProductType.DEXTERITY, new DexterityProductEvolve());
        EVOLVES.put(ProductType.ELIXIR, new ElixirProductEvolve());
        EVOLVES.put(ProductType.SULFURAS, new SulfurasProductEvolve());
        EVOLVES.put(ProductType.CONJURED, new ConjuredProductEvolve());
    }

    private ProductEvolveProvider() {
    }

    public static ProductEvolve getEvolve(ProductType productType) {
        return EVOLVES.get(
                Optional.ofNullable(productType)
                .orElseThrow(() -> new GiledRoseProductException("ProductType cannot be null to find ProductEvolve"))
        );
    }

}
