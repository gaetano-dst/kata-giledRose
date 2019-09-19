package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;
import org.junit.Assert;
import org.junit.Test;

import static com.gildedrose.ItemBuilder.builder;

public class StandardProductEvolveTest {

    private StandardProductEvolve standardProductEvolve = new StandardProductEvolve();

    @Test
    public void getNextSellInValue_decrements_sellIn_by_1() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name("OTHER")
            .sellIn(3)
            .quality(10)
            .build());

        Assert.assertEquals(2, standardProductEvolve.getNextSellInValue(itemWrapper));
    }

    @Test
    public void getNextQualityValue_decrements_quality_by_1() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name("OTHER")
            .sellIn(3)
            .quality(10)
            .build());

        Assert.assertEquals(9, standardProductEvolve.getNextQualityValue(itemWrapper));
    }
}
