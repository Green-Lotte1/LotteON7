package kr.co.lotteon.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/product/cart")
    public String cart(){
        return "product/cart";
    }
}
