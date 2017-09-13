package com.chatRobot.mapper;

import com.chatRobot.po.ItemsCustom;
import com.chatRobot.po.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {
    //商品的查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

}
