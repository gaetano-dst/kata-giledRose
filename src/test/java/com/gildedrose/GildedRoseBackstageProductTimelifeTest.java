package com.gildedrose;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import static com.gildedrose.ItemBuilder.builder;

public class GildedRoseBackstageProductTimelifeTest {

    private static final int AGING_DAYS_4 = 4;
    private static final int AGING_DAYS_5 = 5;
    private static final int AGING_DAYS_7 = 7;

    private static final int[] EXPECTED_SELL_IN_DECREMENTS_BY_ONE =  { 15, 14, 13, 12 };
    private static final int[] EXPECTED_QUALITY_INCREMENTS_BY_ONE_WHEN_SELL_IN_MORE_THAN_10 = { 20, 21, 22, 23 };

    private static final int[] EXPECTED_SELL_IN_DECREMENTS_BY_ONE_WHEN_DAYS_BETWEEN_5_AND_10 =  { 11, 10, 9, 8, 7 };
    private static final int[] EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_DAYS_BETWEEN_5_AND_10 = { 20, 21, 23, 25, 27 };

    private static final int[] EXPECTED_SELL_IN_DECREMENTS_BY_ONE_WHEN_DAYS_BETWEEN_0_AND_5 =  { 6, 5, 4, 3, 2, 1, 0 };
    private static final int[] EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_DAYS_BETWEEN_0_AND_5 = { 20, 22, 25, 28, 31, 34, 37 };

    private static final int[] EXPECTED_QUALITY_NEVER_MORE_THAN_50 = { 49, 50, 50, 50 };

    private static final int[] EXPECTED_SELL_IN_DECREMENTS_BY_ONE_EVEN_WHEN_EXPIRED =  { 0, -1, -2, -3 };
    private static final int[] EXPECTED_QUALITY_IS_ZERO_WHEN_EXPIRED = { 49, 0, 0, 0 };


    @Test
    public void updateQuality_on_single_item_Backstage_product_gives_decrement_sellIn_and_increment_quality_both_by_one_when_more_than_10_days_of_expiration() {
        GildedRose app = new GildedRose(new Item[] {
            builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(15)
                .quality(20)
                .build()
        });

        Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[0]);
        Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_ONE_WHEN_SELL_IN_MORE_THAN_10[0]);

        IntStream.range(1, AGING_DAYS_4)
                .forEach(day -> {
                    app.updateQuality();
                    Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[day]);
                    Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_ONE_WHEN_SELL_IN_MORE_THAN_10[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Backstage_product_gives_decrements_sellIn_by_one_and_quality_increments_by_two_when_sellIn_between_5_and_10() {
        GildedRose app = new GildedRose(new Item[] {
            builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(11)
                .quality(20)
                .build()
        });

        Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE_WHEN_DAYS_BETWEEN_5_AND_10[0]);
        Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_DAYS_BETWEEN_5_AND_10[0]);

        IntStream.range(1, AGING_DAYS_5)
                .forEach(day -> {
                    app.updateQuality();
                    Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE_WHEN_DAYS_BETWEEN_5_AND_10[day]);
                    Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_DAYS_BETWEEN_5_AND_10[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Backstage_product_gives_decrements_sellIn_by_one_and_quality_increments_by_three_when_sellIn_between_0_and_5() {
        GildedRose app = new GildedRose(new Item[] {
            builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(6)
                .quality(20)
                .build()
        });

        Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE_WHEN_DAYS_BETWEEN_0_AND_5[0]);
        Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_DAYS_BETWEEN_0_AND_5[0]);

        IntStream.range(1, AGING_DAYS_7)
                .forEach(day -> {
                    app.updateQuality();
                    Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE_WHEN_DAYS_BETWEEN_0_AND_5[day]);
                    Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_INCREMENTS_BY_TWO_WHEN_DAYS_BETWEEN_0_AND_5[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Backstage_product_gives_quality_never_more_than_50() {
        GildedRose app = new GildedRose(new Item[] {
            builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(15)
                .quality(49)
                .build()
        });

        Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[0]);
        Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_NEVER_MORE_THAN_50[0]);

        IntStream.range(1, AGING_DAYS_4)
                .forEach(day -> {
                    app.updateQuality();
                    Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE[day]);
                    Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_NEVER_MORE_THAN_50[day]);
                });
    }

    @Test
    public void updateQuality_on_single_item_Backstage_product_gives_quality_0_when_expired() {
        GildedRose app = new GildedRose(new Item[] {
            builder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .sellIn(0)
                .quality(49)
                .build()
        });

        Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE_EVEN_WHEN_EXPIRED[0]);
        Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_IS_ZERO_WHEN_EXPIRED[0]);

        IntStream.range(1, AGING_DAYS_4)
                .forEach(day -> {
                    app.updateQuality();
                    Assert.assertEquals(app.items[0].sellIn, EXPECTED_SELL_IN_DECREMENTS_BY_ONE_EVEN_WHEN_EXPIRED[day]);
                    Assert.assertEquals(app.items[0].quality, EXPECTED_QUALITY_IS_ZERO_WHEN_EXPIRED[day]);
                });
    }

}
