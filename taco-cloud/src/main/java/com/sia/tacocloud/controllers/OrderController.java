package com.sia.tacocloud.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sia.tacocloud.data.jpa.OrderRepository;
import com.sia.tacocloud.models.Order;
import com.sia.tacocloud.props.OrderProps;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController
{
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderRepository orderRepo;
    @Autowired
    private OrderProps props;
    @Autowired
    public OrderController(OrderRepository orderRepo)
    {
        super();
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute Order order)
    {
    	log.info("Page size property: {}", props.getPagesize());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus)
    {
        if (errors.hasErrors())
        {
            return "orderForm";
        }

        Order saved = orderRepo.save(order);
        log.info("Created order " + saved);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
