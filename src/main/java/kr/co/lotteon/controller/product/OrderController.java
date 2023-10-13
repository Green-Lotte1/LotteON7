package kr.co.lotteon.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/product/order")
    public String order(){
        return "product/order";
    }
}
