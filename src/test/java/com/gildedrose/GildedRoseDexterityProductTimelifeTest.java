package com.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.gildedrose.ItemBuilder.builder;
import static com.gildedrose.ProductType.DEXTERITY;

public class GildedRoseDexterityProductTimelifeTest {

    private static final int AGING_DAYS_2 = 2;
    private static final int AGING_DAYS_3 = 3;

    private static final int[] EXPECTED_SELL_IN_DECREMENTS_BY_ONE = new int[] { 10, 9 };
    private static final int[] EXPECTED_QUALITY_DECREMENTS_BY_ONE = new int[] { 20, 19 };

    private static final int[] EXPECTED_SELL_IN_ALWAYS_DECREMENTS_BY_ONE = new int[] { 0, -1, -2 };
    private static final int[] EXPECTED_QUALITY_DECREMENTS_BY_TWO_WHEN_EXPIRED = new int[] { 20, 18, 16 };

    private static final int[] EXPECTED_QUALITY_NEVER_LESS_THAN_0 = new int[] { 1, 0, 0 };

    @Test
    public void updateQuality_on_single_item_Dexterity_product_decrements_both_sellIn_and_quality_by_one() {
        Item[] items = new Item[]{builder()
                .name(DEXTERITY.getName())
                .sellIn(10)
                .quality(20)
                .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_DECREMENTS_BY_ONE[0]);

        IntStream.range(1, AGING_DAYS_2)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_DECREMENTS_BY_ONE[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Dexterity_product_decrements_sellIn_by_one_and_quality_by_two_when_sellIn_is_expired() {
            Item[] items = new Item[]{builder()
                    .name(DEXTERITY.getName())
                    .sellIn(0)
                    .quality(20)
                    .build()
            };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENTS_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_DECREMENTS_BY_TWO_WHEN_EXPIRED[0]);

        IntStream.range(1, AGING_DAYS_3)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENTS_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_DECREMENTS_BY_TWO_WHEN_EXPIRED[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Dexterity_product_quality_is_never_less_than_0() {
                Item[] items = new Item[]{builder()
                        .name(DEXTERITY.getName())
                        .sellIn(0)
                        .quality(1)
                        .build()
                };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENTS_BY_ONE[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_NEVER_LESS_THAN_0[0]);

        IntStream.range(1, AGING_DAYS_3)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_DECREMENTS_BY_ONE[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_NEVER_LESS_THAN_0[day]);
                });
    }
}
