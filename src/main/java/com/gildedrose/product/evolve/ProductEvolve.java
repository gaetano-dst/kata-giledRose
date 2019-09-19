package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;

interface ProductEvolve {

    int getNextSellInValue(ItemWrapper itemWrapper);

    int getNextQualityValue(ItemWrapper itemWrapper);

}
