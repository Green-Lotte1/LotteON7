package kr.co.lotteon.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/product/view")
    public String view(){
        return "product/view";
    }
}
