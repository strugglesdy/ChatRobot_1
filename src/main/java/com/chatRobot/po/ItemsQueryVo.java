package com.chatRobot.po;

import java.util.List;

//商品包装对象
public class ItemsQueryVo {
    //商品信息
    private Items items;

    //为了系统的可扩展性，对原始生成的po进行扩展
    private ItemsCustom itemsCustom;

    //商品信息集合
    private List<ItemsCustom> itemsList;


    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public ItemsCustom getItemsCustom() {
        return itemsCustom;
    }

    public void setItemsCustom(ItemsCustom itemsCustom) {

        this.itemsCustom = itemsCustom;
    }

    public List<ItemsCustom> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<ItemsCustom> itemsList) {
        this.itemsList = itemsList;
    }
}
