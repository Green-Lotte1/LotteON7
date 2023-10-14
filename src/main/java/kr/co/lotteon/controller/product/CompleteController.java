package kr.co.lotteon.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompleteController {

    @GetMapping("/product/complete")
    public String complete(){
        return "product/complete";
    }
}
