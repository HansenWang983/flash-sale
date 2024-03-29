package com.wangzehao.flashsale.controller;

import com.wangzehao.flashsale.domain.OrderInfo;
import com.wangzehao.flashsale.domain.SaleUser;
import com.wangzehao.flashsale.redis.prefix.GoodsKey;
import com.wangzehao.flashsale.service.GoodsService;
import com.wangzehao.flashsale.service.OrderService;
import com.wangzehao.flashsale.vo.GoodsVo;
import com.wangzehao.flashsale.vo.OrderDetailVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

//    @RequestMapping(value = "/to_detail", produces = "text/html")
//    public String toDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
//        return render(request, response, model, "order_detail");
//    }

    @RequestMapping("/detail")
    @ResponseBody
    public OrderDetailVo orderInfo(Model model, SaleUser user, @RequestParam("orderId") long orderId) {
        if (user == null) {
            return null;
        }
        OrderInfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return null;
        }
        long goodsId = order.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setOrder(order);
        orderDetailVo.setGoods(goods);
        return orderDetailVo;
    }

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, SaleUser user){
        if(user == null){
            return "User has not logged in.";
        }
        model.addAttribute("user", user);
        List<OrderInfo> orderList = orderService.getOrderListByUserId(user.getNickname());
        model.addAttribute("orderList", orderList);
        return render(request, response, model, "order_list");
    }
}
