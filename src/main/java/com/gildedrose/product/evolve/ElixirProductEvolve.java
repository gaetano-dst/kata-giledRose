package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;

class ElixirProductEvolve extends StandardProductEvolve {

    @Override
    public int getNextQualityValue(ItemWrapper itemWrapper) {
        int newQuality = itemWrapper.getItem().sellIn >= SELL_IN_ZERO
                ? itemWrapper.getItem().quality - UNIT_BY_1
                : itemWrapper.getItem().quality - UNIT_BY_2;

            return Math.max(newQuality, MIN_QUALITY);
    }

}
