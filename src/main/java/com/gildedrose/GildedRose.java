package com.gildedrose;

import com.gildedrose.product.evolve.ProductTimelife;
import com.gildedrose.utils.ItemUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.gildedrose.product.evolve.ProductEvolveProvider.getEvolve;

class GildedRose {
    // items property may not be altered by the requirements !
    Item[] items;

    GildedRose(Item[] items) {
        this.items = copyItems(items);
    }

    void updateQuality() {
        this.items = Arrays.stream(this.items)
                .map(ItemWrapper::new)
             .map(itemWrapper -> {
                    return new ProductTimelife(getEvolve(itemWrapper.getProductType())).apply(itemWrapper);
                })
                .map(ItemWrapper::getItem)
                .toArray(Item[]::new);
    }

    private Item[] copyItems(Item[] items) {
        return Optional.ofNullable(items)
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .map(ItemUtils::getCopy)
                .toArray(Item[]::new);
    }

}