package kr.co.lotteon.controller.admin.cs;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.entity.admin.cs.CsArticleEntity;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequest;
import kr.co.lotteon.response.admin.cs.CsArticleResponse;
import kr.co.lotteon.service.admin.cs.CsArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/cs")
public class AdminCsController {

    private final CsArticleService csArticleService;


    /*  admin/cs/notice  */

    @GetMapping("/notice/list")
    public String notice_list(Model model){

        List<CsArticleResponse> articles =  csArticleService.findAll();
        model.addAttribute("articles",articles);
        log.info("articles :"+articles.toString());
        return "admin/cs/notice/list";
    }

    @GetMapping("/notice/write")
    public String notice_write(){
        return "admin/cs/notice/write";
    }

    @PostMapping("/notice/write")
    public String notice_write(CsArticleCreateRequest csArticleCreateRequest, HttpServletRequest request){
        csArticleCreateRequest.setRegip(request.getRemoteAddr());
        log.info("Notice_write DTO : "+csArticleCreateRequest.toString());
        csArticleService.save(csArticleCreateRequest);
        return  "admin/cs/notice/list";
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
