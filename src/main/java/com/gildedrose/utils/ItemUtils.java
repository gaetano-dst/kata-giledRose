package com.gildedrose.utils;

import com.gildedrose.Item;
import com.gildedrose.ItemBuilder;

public class ItemUtils {

    private ItemUtils() {
    }

    public static Item getCopy(Item item) {
        return ItemBuilder
            .builder()
            .name(item.name)
            .sellIn(item.sellIn)
            .quality(item.quality)
            .build();
    }

    public static Item getCopyWithNewSellIn(Item item, int newSellIn) {
        return ItemBuilder
            .builder()
            .name(item.name)
            .sellIn(newSellIn)
            .quality(item.quality)
            .build();
    }

    public static Item getCopyWithNewQuality(Item item, int newQuality) {
        return ItemBuilder
            .builder()
            .name(item.name)
            .sellIn(item.sellIn)
            .quality(newQuality)
            .build();
    }

}
