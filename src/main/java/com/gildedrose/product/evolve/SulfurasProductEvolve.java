package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;

class SulfurasProductEvolve implements ProductEvolve {

    private static final int MAX_QUALITY_SULFURAS = 80;

    @Override
    public int getNextSellInValue(ItemWrapper itemWrapper) {
        return itemWrapper.getItem().sellIn;
    }

    @Override
    public int getNextQualityValue(ItemWrapper itemWrapper) {
        return MAX_QUALITY_SULFURAS;
    }
}
