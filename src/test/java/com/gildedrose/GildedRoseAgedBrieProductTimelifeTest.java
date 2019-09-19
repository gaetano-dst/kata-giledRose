package com.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.gildedrose.ItemBuilder.builder;
import static com.gildedrose.ProductType.AGED_BRIE;

public class GildedRoseAgedBrieProductTimelifeTest {

    private static final int AGING_DAYS_2 = 2;
    private static final int AGING_DAYS_3 = 3;

    private static final int[] EXPECTED_SELL_IN_DECREMENT_BY_ONE = new int[] { 2, 1 };
    private static final int[] EXPECTED_QUALITY_INCREMENTS_BY_ONE = new int[] { 0, 1 };

    private static final int[] EXPECTED_SELL_IN_ALWAYS_DECREMENT_BY_ONE = new int[] { 0, -1, -2 };
    private static final int[] EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_EXPIRED = new int[] { 0, 2, 4 };

    private static final int[] EXPECTED_QUALITY_NEVER_MORE_THAN_50 = new int[] { 49, 50, 50 };


    @Test
    public void updateQuality_on_single_item_Brie_decrements_sellIn_and_increments_quality_by_one() {
        Item[] items = new Item[] { builder()
            .name(AGED_BRIE.getName())
            .sellIn(2)
            .quality(0)
            .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENT_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_ONE[0]);

        IntStream.range(1, AGING_DAYS_2)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENT_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_ONE[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Brie_decrements_sellIn_and_increments_quality_by_two_when_sellIn_is_expired() {
        Item[] items = new Item[] { builder()
            .name(AGED_BRIE.getName())
            .sellIn(0)
            .quality(0)
            .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENT_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_EXPIRED[0]);

        IntStream.range(1, AGING_DAYS_3)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENT_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_EXPIRED[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Brie_quality_is_never_more_than_50() {
        Item[] items = new Item[] { builder()
            .name(AGED_BRIE.getName())
            .sellIn(0)
            .quality(49)
            .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENT_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_NEVER_MORE_THAN_50[0]);

        IntStream.range(1, AGING_DAYS_3)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENT_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_NEVER_MORE_THAN_50[day]);
                });
    }
}
