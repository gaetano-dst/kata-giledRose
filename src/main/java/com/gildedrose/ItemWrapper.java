package com.gildedrose;

import com.gildedrose.exceptions.GiledRoseProductException;
import com.gildedrose.utils.ItemUtils;

import static com.gildedrose.ProductType.fromName;

public class ItemWrapper {

    public static final String AGED_BRIE_NAME = "Aged Brie";
    public static final String BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_NAME = "Sulfuras, Hand of Ragnaros";
    public static final String DEXTERITY_NAME = "+5 Dexterity Vest";
    public static final String ELIXIR_NAME = "Elixir of the Mongoose";

    private final ProductType productType;
    private final Item item;

    public ItemWrapper(Item item) {
        validateItem(item);
        this.productType = fromName(item.name);
        this.item = ItemUtils.getCopy(item);
    }

    private static void validateItem(Item item) {
        if(item == null) {
            throw new GiledRoseProductException("Null Item is not allowed to create ItemWrapper");
        }
    }

    public Item getItem() {
        return item;
    }

    public ProductType getProductType() {
        return productType;
    }
}
