package com.kobobook.www.admin.web;

import com.kobobook.www.admin.domain.Order;
import com.kobobook.www.admin.dto.OrderDTO;
import com.kobobook.www.admin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("/allOrderList")
    public String readAllOrderList(Model model) {
        System.out.println("order");
        return "order/orderList";
    }
//
//    @GetMapping("/biginOrderList")
//    public String readBeginOrderList(Model model) {
//        Page<OrderDTO> orderList = orderService.readBeginOrderList();
//        model.addAttribute("beginOrderList", orderList.getContent());
//        return "order/biginOrderList";
//    }

}
