package kr.co.lotteon.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListController {

    @GetMapping("/product/list")
    public String list(){
        return "product/list";
    }
}
