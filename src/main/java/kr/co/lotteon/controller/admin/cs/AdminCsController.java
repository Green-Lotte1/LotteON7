package kr.co.lotteon.controller.admin.cs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/cs")
public class AdminCsController {


    /*  admin/cs/notice  */

    @GetMapping("/notice/list")
    public String notice_list(){
        return "admin/cs/notice/list";
    }

    @GetMapping("/notice/write")
    public String notice_write(){
        return "admin/cs/notice/write";
    }

    /*  admin/cs/qna  */
    @GetMapping("/qna/list")
    public String qna_list(){
        return "admin/cs/qna/list";
    }

    /*  admin/cs/faq  */
    @GetMapping("/faq/list")
    public String faq_list(){
        return "admin/cs/faq/list";
    }
}
