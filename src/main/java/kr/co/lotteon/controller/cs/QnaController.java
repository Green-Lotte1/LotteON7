package kr.co.lotteon.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qna")
public class QnaController {

    @GetMapping("/list")
    public String list(){
        return "cs/qna/list";
    }

    @GetMapping("/view")
    public String view(){
        return "cs/qna/view";
    }

    @GetMapping("/write")
    public String write(){
        return "cs/qna/write";
    }
}
