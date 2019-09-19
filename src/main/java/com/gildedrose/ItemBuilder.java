package com.gildedrose;

public class ItemBuilder {

    private String name;
    private int sellIn;
    private int quality;


    private ItemBuilder() {
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }


    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder sellIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    public ItemBuilder  quality(int quality) {
        this.quality = quality;
        return this;
    }

    public Item build() {
        return new Item(this.name, this.sellIn, this.quality);
    }
}
