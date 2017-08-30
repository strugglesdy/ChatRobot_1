package com.chatRobot.controller;

import com.chatRobot.controller.validation.ValidationGroup1;
import com.chatRobot.exception.CustomException;
import com.chatRobot.po.Items;
import com.chatRobot.po.ItemsCustom;
import com.chatRobot.po.ItemsQueryVo;
import com.chatRobot.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//该类是一个controller层的类
@Controller
//为了对url进行分类管理，可以在这里定义根路径，最终访问url是根路径加子路径
@RequestMapping("/items")
public class ItemsController {
    //@autowired该注解表示让spring自动注入该bean
    @Autowired
    private ItemsService itemsService;

    //商品查询
    @RequestMapping("/queryItems")
    //包装类型pojo参数绑定
    public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {
        //测试forward后request是否可以共享
        System.out.println(request.getParameter("id"));

        //调用service查找数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //相当于request的setAttribute，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);
        //指定视图
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }

    //商品信息修改页面显示
    //@RequestMapping("/editItems")
    //限制http请求方法，可以post和get
//    @RequestMapping(value = "/editItems",method = {RequestMethod.POST,RequestMethod.GET})
//    public ModelAndView editItems() throws Exception {
//        //调用service根据商品id查询商品信息
//        ItemsCustom itemsCustom = itemsService.findItemsById(1);
//        //返回ModelAndView
//        ModelAndView modelAndView = new ModelAndView();
//        //将商品信息放到model
//        modelAndView.addObject("itemsCustom", itemsCustom);
//        //商品修改页面
//        modelAndView.setViewName("items/editItems");
//        return modelAndView;
//    }
    @RequestMapping(value = "/editItems",method = {RequestMethod.POST,RequestMethod.GET})
    //用@RequestParam注解（如果请求参数的key和Controller类中方法的形参名称不一致）  required属性指定参数是否必须传入
    public String editItems(Model model, @RequestParam(value = "id",required = true ) Integer items_id) throws Exception {
        //调用service根据商品id查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
        //判断商品是否为空，若id没有查询到商品，抛出异常，提示用户商品信息不存在
        if(itemsCustom == null) {
            throw new CustomException("商品信息不存在");
        }
        //通过形参中model将model数据传到页面，相当于modelAndView.addObject方法
        model.addAttribute("itemsCustom", itemsCustom);
        return "items/editItems";//返回逻辑视图名  真正视图（jsp路径）=前缀+逻辑视图名+后缀
    }

    //商品信息修改提交
    @RequestMapping("/editItemsSubmit")
    //绑定pojo的参数时，要求jsp中input框的name值要和Controller方法形参的pojo对象中的属性名称一致
//    public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom) throws Exception {
    //在需要校验的pojo前面加@Validated，后面加BindingResult bindingResult接收校验出错信息（两者配对出现 一前一后）
    //value = {ValidationGroup1.class}指定使用ValidationGroup1分组的校验
    public String editItemsSubmit(Model model, HttpServletRequest request, Integer id,
          @Validated(value = {ValidationGroup1.class}) ItemsCustom itemsCustom, BindingResult bindingResult) throws Exception {
        //获取校验错误
        if (bindingResult.hasErrors()) {
            //输出错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError:allErrors) {
                //输出错误信息
                System.out.println(objectError.getDefaultMessage());
            }
            //将错误信息传到页面
            model.addAttribute("allErrors", allErrors);

            return "items/editItems";
        }

        //调用service更新商品信息,页面需要将商品信息传到此方法
        itemsService.updateItems(id, itemsCustom);

        //redirect重定向        (url地址栏会变化,修改提交的request数据无法传到重定向的地址，因为重定向后重新进行request（request无法共享）到商品查询列表
//        return "redirect:queryItems.do";
        //forward页面转发       （url地址栏不变,request可以共享）
//        return "forward:queryItems.do";
      return "success";
    }

    //批量删除商品信息（使用数组来接收）
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception {
        //调用service删除商品信息
        //...

        return "success";
    }

    //批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("items/editItemsQuery");
        return modelAndView;
    }

    //批量修改商品提交（使用List来接收）
    //通过ItemsQueryVo接收批量提交商品信息，将商品信息存储到itemsQueryVo中itemsList属性中
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {

        return "success";
    }
}
