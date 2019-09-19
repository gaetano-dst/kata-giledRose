package com.gildedrose;

import java.util.Arrays;

public enum ProductType {

    STANDARD(""),
    AGED_BRIE("Aged Brie"),
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert"),
    DEXTERITY("+5 Dexterity Vest"),
    ELIXIR("Elixir of the Mongoose"),
    SULFURAS("Sulfuras, Hand of Ragnaros");
    // TODO: Create new feature "Conjured Mana Cake"

    private final String name;

    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProductType fromName(String searchedName) {
        return Arrays.stream(ProductType.values())
                .filter(p -> p.getName().equals(searchedName))
                .findFirst()
                .orElse(ProductType.STANDARD);
    }
}
