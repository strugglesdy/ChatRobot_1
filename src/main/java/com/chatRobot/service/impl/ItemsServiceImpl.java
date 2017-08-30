package com.chatRobot.service.impl;

import com.chatRobot.mapper.ItemsMapper;
import com.chatRobot.mapper.ItemsMapperCustom;
import com.chatRobot.po.Items;
import com.chatRobot.po.ItemsCustom;
import com.chatRobot.po.ItemsQueryVo;
import com.chatRobot.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//商品管理
@Service
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        //通过ItemsMapperCustom查询数据库
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        //中间对商品信息进行业务处理
        //。。。。。。
        //返回ItemsCustom
        ItemsCustom itemsCustom = null;
        //将items的属性值拷贝到itemsCustom
        if (items!=null){
            itemsCustom = new ItemsCustom();
            BeanUtils.copyProperties(items,itemsCustom);
        }
        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //添加业务校验，通常在service接口对关键参数进行校验
        //校验id是否为空，如果空抛出异常   int无法判断是否为空，因此接口用Interger而不是int

        //更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中的所有字段，包括大文本类型字段
        //updateByPrimaryKeyWithBLOBs要求必须传入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
