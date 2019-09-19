package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;

class ConjuredProductEvolve extends StandardProductEvolve {

    @Override
    public int getNextQualityValue(ItemWrapper itemWrapper) {
        int newQuality = itemWrapper.getItem().quality - UNIT_BY_2;
        return Math.max(newQuality, MIN_QUALITY);
    }
}
