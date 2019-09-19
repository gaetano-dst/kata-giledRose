package com.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

import static com.gildedrose.ItemBuilder.builder;
import static com.gildedrose.ItemWrapper.SULFURAS_NAME;

public class GildedRoseSulfurasProductTimelifeTest {

    private static final int AGING_DAYS_4 = 4;

    // TODO: Check with business -> I think, this is a bug. Days should decrement in time life !
    private static final int[] EXPECTED_SELL_IN_NEVER_DECREMENTS = new int[] { 0, 0, 0, 0 };
    private static final int EXPECTED_QUALITY_ALWAYS_80 = 80;

    private static final int[] EXPECTED_SELL_IN_ALWAYS_MINUS_1_WHEN_EXPIRED = new int[] { -1, -1, -1, -1 };


    @Test
    public void updateQuality_on_items_Sulfuras_products_give_always_same_sellIn_and_quality_to_80() {
        Item[] items = new Item[]{builder()
                .name(SULFURAS_NAME)
                .sellIn(0)
                .quality(80)
                .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_NEVER_DECREMENTS[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_ALWAYS_80);

        IntStream.range(1, AGING_DAYS_4)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_NEVER_DECREMENTS[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_ALWAYS_80);
                });
    }

    @Test
    public void updateQuality_on_items_Sulfuras_products_give_always_same_sellIn_and_quality_when_sellIn_expired() {
        Item[] items = new Item[]{builder()
                .name(SULFURAS_NAME)
                .sellIn(-1)
                .quality(80)
                .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_MINUS_1_WHEN_EXPIRED[0]);
        Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_ALWAYS_80);

        IntStream.range(1, AGING_DAYS_4)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, EXPECTED_SELL_IN_ALWAYS_MINUS_1_WHEN_EXPIRED[day]);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, EXPECTED_QUALITY_ALWAYS_80);
                });
    }

    @Test
    public void updateQuality_on_items_Sulfuras_products_should_always_be_80() {
        Item[] items = new Item[]{builder()
                .name(SULFURAS_NAME)
                .sellIn(3)
                .quality(10)
                .build()
        };

        GildedRose gildedRoseBis = new GildedRose(items);

        Assert.assertEquals(gildedRoseBis.items[0].sellIn, 3);
        Assert.assertEquals(gildedRoseBis.items[0].quality, 10);

        IntStream.range(1, AGING_DAYS_4)
                .forEach(day -> {
                    gildedRoseBis.updateQuality();
                    Assert.assertEquals(gildedRoseBis.items[0].sellIn, 3);
                    Assert.assertEquals(gildedRoseBis.items[0].quality, 80);
                });
    }

}
