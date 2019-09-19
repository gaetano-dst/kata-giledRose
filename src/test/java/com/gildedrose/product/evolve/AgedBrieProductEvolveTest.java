package com.gildedrose.product.evolve;

import com.gildedrose.ItemWrapper;
import org.junit.Assert;
import org.junit.Test;

import static com.gildedrose.ItemBuilder.builder;
import static com.gildedrose.ProductType.AGED_BRIE;

public class AgedBrieProductEvolveTest {

    private AgedBrieProductEvolve agedBrieProductEvolve = new AgedBrieProductEvolve();

    @Test
    public void getNextSellInValue_decrements_sellIn_by_1() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name(AGED_BRIE.getName())
            .sellIn(3)
            .quality(10)
            .build());

        Assert.assertEquals(2, agedBrieProductEvolve.getNextSellInValue(itemWrapper));
    }

    @Test
    public void getNextQualityValue_increments_quality_by_2_when_sellIn_less_than_0() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name(AGED_BRIE.getName())
            .sellIn(-1)
            .quality(10)
            .build());

        Assert.assertEquals(12, agedBrieProductEvolve.getNextQualityValue(itemWrapper));
    }

    @Test
    public void getNextQualityValue_increments_quality_by_1_when_sellIn_more_than_0() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name(AGED_BRIE.getName())
            .sellIn(1)
            .quality(10)
            .build());

        Assert.assertEquals(11, agedBrieProductEvolve.getNextQualityValue(itemWrapper));
    }

    @Test
    public void getNextQualityValue_never_gives_quality_more_than_50() {
        ItemWrapper itemWrapper = new ItemWrapper(builder()
            .name(AGED_BRIE.getName())
            .sellIn(2)
            .quality(50)
            .build());

        Assert.assertEquals(50, agedBrieProductEvolve.getNextQualityValue(itemWrapper));
    }
}
