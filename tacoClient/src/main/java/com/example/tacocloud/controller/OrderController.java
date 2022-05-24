package com.example.tacocloud.controller;

import javax.validation.Valid;

import com.example.tacocloud.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private RestTemplate rest = new RestTemplate();
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        rest.postForObject("http://localhost:8080/orders", order, Order.class);
        return "redirect:/";
    }
    @GetMapping("/list")
    public String orderList(Model model) {
        Order[] orders = rest.getForObject("http://localhost:8080/orders", Order[].class);
        model.addAttribute("orders", orders);
        return "orderList";
    }
}
