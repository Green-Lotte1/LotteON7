package kr.co.lotteon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }


    @GetMapping("/admin/index")
    public String admin_index(){
        return"admin/index";
    }
}
