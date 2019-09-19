package com.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.gildedrose.ItemBuilder.builder;
import static com.gildedrose.ProductType.CONJURED;

public class GildedRoseConjuredProductTimelifeTest {

    private static final int AGING_DAYS_3 = 3;

    private static final int[] EXPECTED_SELL_IN_DECREMENTS_BY_ONE = new int[] { 10, 9, 8 };
    private static final int[] EXPECTED_QUALITY_DECREMENTS_BY_TWO = new int[] { 20, 18, 16 };

    private static final int[] EXPECTED_QUALITY_NEVER_LESS_THAN_0 = new int[] { 1, 0, 0 };

    @Test
    public void updateQuality_on_single_item_Conjured_product_gives_decrement_sellIn_bby_one_and_quality_by_two() {
        Item[] items = new Item[]{ builder()
                .name(CONJURED.getName())
                .sellIn(10)
                .quality(20)
                .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_DECREMENTS_BY_TWO[0]);

        IntStream.range(1, AGING_DAYS_3)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_DECREMENTS_BY_TWO[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Conjured_product_quality_is_never_less_than_0() {
        Item[] items = new Item[]{builder()
                .name(CONJURED.getName())
                .sellIn(10)
                .quality(1)
                .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_NEVER_LESS_THAN_0[0]);

        IntStream.range(1, AGING_DAYS_3)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_NEVER_LESS_THAN_0[day]);
                });
    }
}
