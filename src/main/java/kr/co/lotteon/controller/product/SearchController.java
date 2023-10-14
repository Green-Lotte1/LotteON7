package kr.co.lotteon.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    @GetMapping("/product/search")
    public String search(){
        return "product/search";
    }
}
