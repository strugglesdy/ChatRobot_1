package com.chatRobot.service;

import com.chatRobot.po.Items;
import com.chatRobot.po.ItemsCustom;
import com.chatRobot.po.ItemsQueryVo;

import java.util.List;

public interface ItemsService {
    //查询items列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
    //根据id查询商品信息 id 查询商品的id
    public ItemsCustom findItemsById(Integer id) throws Exception;
    //修改商品信息   id 修改商品的id
    public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;


}
