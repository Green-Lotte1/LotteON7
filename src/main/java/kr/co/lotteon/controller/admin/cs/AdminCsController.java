package kr.co.lotteon.controller.admin.cs;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequestDTO;
import kr.co.lotteon.request.admin.cs.CsArticlePageRequestDTO;
import kr.co.lotteon.response.admin.cs.CsArticlePageResponseDTO;
import kr.co.lotteon.response.admin.cs.CsArticleResponseDTO;
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
    public String notice_list(Model model, CsArticlePageRequestDTO pageRequestDTO){

        CsArticlePageResponseDTO pageResponseDTO = csArticleService.findByCate(pageRequestDTO);

        log.info("pageResponseDTO pg : " + pageResponseDTO.getPg());
        log.info("pageResponseDTO size : " + pageResponseDTO.getSize());
        log.info("pageResponseDTO total : " + pageResponseDTO.getTotal());
        log.info("pageResponseDTO start : " + pageResponseDTO.getStart());
        log.info("pageResponseDTO end : " + pageResponseDTO.getEnd());
        log.info("pageResponseDTO prev : " + pageResponseDTO.isPrev());
        log.info("pageResponseDTO next : " + pageResponseDTO.isNext());

        return null;
    }

    @GetMapping("/notice/write")
    public String notice_write(){
        return "admin/cs/notice/write";
    }

    @PostMapping("/notice/write")
    public String notice_write(CsArticleCreateRequestDTO csArticleCreateRequestDTO, HttpServletRequest request){
        csArticleCreateRequestDTO.setRegip(request.getRemoteAddr());
        log.info("Notice_write DTO : "+ csArticleCreateRequestDTO.toString());
        csArticleService.save(csArticleCreateRequestDTO);
        return  "admin/cs/notice/list";
    }


    @GetMapping("/notice/view")
    public String notice_view(){
        return null;
    }
    @GetMapping("/notice/modify")
    public String notice_modify(){
        return null;
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
