package com.kobobook.www.admin.web;

import com.kobobook.www.admin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @GetMapping("")
    public String readOrderList(Model model) {

        return "order/order";
    }

}
