package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;
import org.junit.Assert;
import org.junit.Test;

import static com.gildedrose.ItemBuilder.builder;
import static com.gildedrose.ItemWrapper.SULFURAS_NAME;

public class SulfurasProductEvolveTest {

    private SulfurasProductEvolve sulfurasProductEvolve = new SulfurasProductEvolve();

    @Test
    public void getNextSellInValue_never_change_sellIn_value() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name(SULFURAS_NAME)
            .sellIn(3)
            .quality(10)
            .build());

        Assert.assertEquals(3, sulfurasProductEvolve.getNextSellInValue(itemWrapper));
    }

    @Test
    public void getNextQualityValue_always_give_quality_value_80() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name(SULFURAS_NAME)
            .sellIn(3)
            .quality(10)
            .build());

        Assert.assertEquals(80, sulfurasProductEvolve.getNextQualityValue(itemWrapper));
    }
}
