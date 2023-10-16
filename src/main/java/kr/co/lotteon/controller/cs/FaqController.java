package kr.co.lotteon.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/faq")
public class FaqController {

    @GetMapping("/list")
    public String list(){
        return "cs/faq/list";
    }

    @GetMapping("/view")
    public String view(){
        return "cs/faq/view";
    }

    @PostMapping("/list")
    public String list2(){
        return "redirect:cs/faq/list";
    }
}
