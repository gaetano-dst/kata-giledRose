package com.gildedrose;

import com.gildedrose.exceptions.GiledRoseProductException;
import org.junit.Assert;
import org.junit.Test;

import static com.gildedrose.ProductType.*;

public class ItemWrapperTest {


    @Test(expected = GiledRoseProductException.class)
    public void create_wrapper_give_GiledRoseProductException_when_item_is_null() {
        new ItemWrapper(null);
    }


    @Test
    public void create_wrapper_give_item_with_same_properties_value() {
        ItemWrapper itemWrapper = new ItemWrapper(ItemBuilder.builder()
            .name(AGED_BRIE.getName())
            .sellIn(0)
            .quality(10)
            .build());

        Assert.assertEquals("Aged Brie", itemWrapper.getItem().name);
        Assert.assertEquals(0, itemWrapper.getItem().sellIn);
        Assert.assertEquals(10, itemWrapper.getItem().quality);
    }

    @Test
    public void create_wrapper_give_ProductType_AGED_BRIE_when_name_is_Aged_Brie() {
        ItemWrapper itemWrapper = new ItemWrapper(ItemBuilder.builder()
            .name(AGED_BRIE.getName())
            .sellIn(0)
            .quality(10)
            .build());

        Assert.assertEquals(AGED_BRIE, itemWrapper.getProductType());
    }

    @Test
    public void create_wrapper_give_ProductType_BACKSTAGE_when_name_is_full_Backstage_name() {
        ItemWrapper itemWrapper = new ItemWrapper(ItemBuilder.builder()
            .name(BACKSTAGE.getName())
            .sellIn(0)
            .quality(10)
            .build());

        Assert.assertEquals(BACKSTAGE, itemWrapper.getProductType());
    }

    @Test
    public void create_wrapper_give_ProductType_DEXTERITY_when_name_is_full_Dexterity_name() {
        ItemWrapper itemWrapper = new ItemWrapper(ItemBuilder.builder()
            .name(DEXTERITY.getName())
            .sellIn(0)
            .quality(10)
            .build());

        Assert.assertEquals(DEXTERITY, itemWrapper.getProductType());
    }

    @Test
    public void create_wrapper_give_ProductType_ELIXIR_when_name_is_full_Elixir_name() {
        ItemWrapper itemWrapper = new ItemWrapper(ItemBuilder.builder()
            .name(ELIXIR.getName())
            .sellIn(0)
            .quality(10)
            .build());

        Assert.assertEquals(ELIXIR, itemWrapper.getProductType());
    }

    @Test
    public void create_wrapper_give_ProductType_SULFURAS_when_name_is_full_Sulfuras_name() {
        ItemWrapper itemWrapper = new ItemWrapper(ItemBuilder.builder()
            .name(SULFURAS.getName())
            .sellIn(0)
            .quality(10)
            .build());

        Assert.assertEquals(SULFURAS, itemWrapper.getProductType());
    }


}
