package com.kobobook.www.admin.web;

import com.kobobook.www.admin.domain.OrderSearch;
import com.kobobook.www.admin.dto.MemberDTO;
import com.kobobook.www.admin.dto.OrderDTO;
import com.kobobook.www.admin.dto.OrderItemDTO;
import com.kobobook.www.admin.service.MemberService;
import com.kobobook.www.admin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    private MemberService memberService;

    /*
    * 배송준비중 주문 조회
    * */
    @GetMapping("/readyOrderList")
    public String readReadyOrderList(OrderSearch orderSearch, Model model) {
        List<OrderItemDTO> readyOrderItemList = orderService.readReadyOrderList(orderSearch);
        model.addAttribute("orderSearch", orderSearch);
        model.addAttribute("readyOrderItemList", readyOrderItemList);
        return "order/readyOrderList";
    }

    /*
    * 전체 주문 조회
    * */
    @GetMapping("/allOrderList")
    public String readAllOrderList(OrderSearch orderSearch, Model model) {
        List<OrderItemDTO> allOrderList = orderService.readAllOrderList(orderSearch);
        model.addAttribute("orderSearch", orderSearch);
        model.addAttribute("allOrderList", allOrderList);
        return "order/allOrderList";
    }

    /*
     * 주문 상세 조회
     * */
    @GetMapping("/{id}")
    public String readOrderDetail(@PathVariable("id") Integer orderId, Model model) {
        List<OrderItemDTO> orderItemList = orderService.readOrderDetail(orderId);
        System.out.println("111");
        MemberDTO member = orderItemList.get(0).getOrder().getMember();
        System.out.println("222");
        model.addAttribute("member", member);
        System.out.println("333");
        model.addAttribute("orderItemList", orderItemList);
        System.out.println("444");
        return "order/orderDetail";
    }

}
