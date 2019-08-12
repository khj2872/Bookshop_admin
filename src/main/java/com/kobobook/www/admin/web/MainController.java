package com.kobobook.www.admin.web;

import com.kobobook.www.admin.domain.DeliveryStatus;
import com.kobobook.www.admin.repository.ItemRepository;
import com.kobobook.www.admin.repository.OrderRepository;
import com.kobobook.www.admin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {

    private OrderService orderService;

    private OrderRepository orderRepository;

    private ItemRepository itemRepository;

    private Environment env;

    @GetMapping("/admin/dashboard")
    public String home(Model model) {
        Map<String, Object> orderCount = getOrderCount();

        long todayTotalPrice = orderService.findTodayTotalPrice();
        long yesterdayTotalPrice = orderService.findYesterdayTotalPrice();
        int dayTotalPriceGrowth = getTotalPriceGrowth(todayTotalPrice, yesterdayTotalPrice);

        long curMonthTotalPrice = orderService.findCurMonthTotalPrice();
        long prevMonthTotalPrice = orderService.findPrevMonthTotalPrice();
        int monthTotalPriceGrowth = getTotalPriceGrowth(curMonthTotalPrice, prevMonthTotalPrice);

        long itemCount = itemRepository.selectCountAllItems();

        model.addAttribute("orderCount", orderCount);
        model.addAttribute("todayTotalPrice", todayTotalPrice);//당일 총 매출액
        model.addAttribute("dayTotalPriceGrowth", dayTotalPriceGrowth);//어제 대비 매출액 증가율
        model.addAttribute("curMonthTotalPrice", curMonthTotalPrice); //이번 달 당일까지의 총 매출액
        model.addAttribute("monthTotalPriceGrowth", monthTotalPriceGrowth);//지난 달 대비 매출액 증가율
        model.addAttribute("itemCount", itemCount);
        return "index";
    }

    private Map<String, Object> getOrderCount() {
        Long readyOrderCount = orderRepository.selectCountOrder(DeliveryStatus.READY);
        Long deliveryOrderCount = orderRepository.selectCountOrder(DeliveryStatus.DELIVERY);

        Map<String, Object> orderCount = new HashMap<>();
        orderCount.put("readyOrderCount", readyOrderCount);
        orderCount.put("deliveryOrderCount", deliveryOrderCount);
        return orderCount;
    }

    private int getTotalPriceGrowth(long currentTotalPrice, long previousTotalPrice) {
        if (previousTotalPrice == 0) return -100;
        return (int) ((currentTotalPrice - previousTotalPrice) / previousTotalPrice * 100);
    }

    @GetMapping("/")
    public String main() {
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/profile")
    @ResponseBody
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }

}
