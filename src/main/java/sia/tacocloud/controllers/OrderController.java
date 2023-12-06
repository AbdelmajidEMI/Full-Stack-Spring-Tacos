package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.DAO.orderRepo.JPAOrderRepository;
import sia.tacocloud.DAO.orderRepo.OrderRepository;
import sia.tacocloud.DAO.userRepo.JPAUserRepository;
import sia.tacocloud.modules.Order;
import sia.tacocloud.modules.User;

import java.security.Principal;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final JPAOrderRepository orderRepository;
    private final JPAUserRepository userRepository;

    public OrderController(JPAOrderRepository orderRepository, JPAUserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/current")
    public String orderFrom(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @PostMapping("/current")
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user
            ) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
