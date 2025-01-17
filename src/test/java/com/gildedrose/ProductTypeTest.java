package com.gildedrose;

import org.junit.Assert;
import org.junit.Test;

import static com.gildedrose.ProductType.*;

public class ProductTypeTest {

    @Test
    public void fromName_gives_ProductType_AGED_BRIE_when_Aged_Brie_name() {
        Assert.assertEquals(AGED_BRIE, ProductType.fromName("Aged Brie"));
    }

    @Test
    public void fromName_gives_ProductType_BACKSTAGE_when_Backstage_name() {
        Assert.assertEquals(BACKSTAGE, ProductType.fromName("Backstage passes to a TAFKAL80ETC concert"));
    }

    @Test
    public void fromName_gives_ProductType_DEXTERITY_when_Aged_Brie_name() {
        Assert.assertEquals(DEXTERITY, ProductType.fromName("+5 Dexterity Vest"));
    }

    @Test
    public void fromName_gives_ProductType_ELIXIR_when_Aged_Brie_name() {
        Assert.assertEquals(ELIXIR, ProductType.fromName("Elixir of the Mongoose"));
    }

    @Test
    public void fromName_gives_ProductType_SULFURAS_when_Aged_Brie_name() {
        Assert.assertEquals(SULFURAS, ProductType.fromName("Sulfuras, Hand of Ragnaros"));
    }

    @Test
    public void fromName_gives_ProductType_CONJURED_when_Aged_Brie_name() {
        Assert.assertEquals(CONJURED, ProductType.fromName("Conjured Mana Cake"));
    }

    @Test
    public void fromName_gives_ProductType_STANDARD_when_no_name_corresponding() {
        Assert.assertEquals(STANDARD, ProductType.fromName("OTHER"));
    }
}
