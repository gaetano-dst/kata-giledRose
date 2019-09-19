package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    private static final String FOO_PRODUCT_NAME = "foo";

    @Test
    public void updateQuality_does_alter_item_name() {
        Item[] items = new Item[] { new Item(FOO_PRODUCT_NAME, 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(FOO_PRODUCT_NAME, app.items[0].name);
    }

}
