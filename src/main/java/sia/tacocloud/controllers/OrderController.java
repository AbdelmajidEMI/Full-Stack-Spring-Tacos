package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import org.springframework.validation.Errors;
import sia.tacocloud.modules.Order;


@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/current")
    public String orderFrom(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping("/current")
    public String processOrder(Order order) {
        log.info("0rder submitted: " + order);
        return "redirect:/";
    }
}