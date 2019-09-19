package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;
import com.gildedrose.exceptions.GiledRoseProductException;

import java.util.Optional;
import java.util.function.Function;

import static com.gildedrose.utils.ItemUtils.getCopyWithNewQuality;
import static com.gildedrose.utils.ItemUtils.getCopyWithNewSellIn;

public class ProductTimelife implements Function<ItemWrapper, ItemWrapper> {

    private ProductEvolve productEvolve;

    public ProductTimelife(ProductEvolve productEvolve) {
        this.productEvolve = Optional.ofNullable(productEvolve)
            .orElseThrow(() -> new GiledRoseProductException("ProductEvolve is not allowed in Timelife"));
    }

    @Override
    public ItemWrapper apply(ItemWrapper itemWrapper) {
        ItemWrapper itemWithNewSellIn = applyOnSellIn(itemWrapper);
        return applyOnQuality(itemWithNewSellIn);
    }

    private ItemWrapper applyOnQuality(ItemWrapper product) {
        return new ItemWrapper(getCopyWithNewQuality(product.getItem(), productEvolve.getNextQualityValue(product)));
    }

    private ItemWrapper applyOnSellIn(ItemWrapper product) {
        return new ItemWrapper(getCopyWithNewSellIn(product.getItem(), productEvolve.getNextSellInValue(product)));
    }
}
