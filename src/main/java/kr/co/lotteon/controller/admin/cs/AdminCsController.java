package kr.co.lotteon.controller.admin.cs;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequestDTO;
import kr.co.lotteon.request.admin.cs.CsArticleMultiDeleteRequest;
import kr.co.lotteon.request.admin.cs.CsArticlePageRequestDTO;
import kr.co.lotteon.response.admin.cs.CsArticlePageResponseDTO;
import kr.co.lotteon.response.admin.cs.CsArticleResponseDTO;
import kr.co.lotteon.service.admin.cs.CsArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/cs")
public class AdminCsController {

    private final CsArticleService csArticleService;

/////////////////////////////////////////
////////////////////////////////////////
/*  admin/cs/notice  */
////////////////////////////////////////
///////////////////////////////////////


    @GetMapping("/notice/list")
    public String notice_list(Model model, CsArticlePageRequestDTO pageRequestDTO){
        pageRequestDTO.setCate("notice");
        CsArticlePageResponseDTO pageResponseDTO = csArticleService.findByCate(pageRequestDTO);

        log.info("pageResponseDTO pg : " + pageResponseDTO.getPg());
        log.info("pageResponseDTO size : " + pageResponseDTO.getSize());
        log.info("pageResponseDTO total : " + pageResponseDTO.getTotal());
        log.info("pageResponseDTO start : " + pageResponseDTO.getStart());
        log.info("pageResponseDTO end : " + pageResponseDTO.getEnd());
        log.info("pageResponseDTO prev : " + pageResponseDTO.isPrev());
        log.info("pageResponseDTO next : " + pageResponseDTO.isNext());
        log.info("pageResponseDTO dtoList : " + pageResponseDTO.getDtoList());
        model.addAttribute("pageResponseDTO",pageResponseDTO);
        return "admin/cs/notice/list";
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
        return  "redirect:/admin/cs/notice/list";
    }


    @GetMapping("/notice/view")
    public String notice_view(@RequestParam("articleId") int articleId ,Model model){
        log.info("view ArticleId"+articleId);
        CsArticleResponseDTO responseDTO = csArticleService.findById(articleId);
        log.info("responseDTO :"+responseDTO.toString());
        model.addAttribute("responseDTO",responseDTO);
        return "admin/cs/notice/view";

    }
    @GetMapping("/notice/modify")
    public String notice_modify(@RequestParam("articleId") int articleId, Model model ) {

        CsArticleResponseDTO responseDTO   =  csArticleService.findById(articleId);
        log.info("responseDTO :"+responseDTO.toString());
        model.addAttribute("responseDTO",responseDTO);
        return "admin/cs/notice/modify";
    }

    @PostMapping("/notice/modify")
    public String notice_modify(CsArticleCreateRequestDTO csArticleCreateRequestDTO, HttpServletRequest request){
        log.info(csArticleCreateRequestDTO.toString());
        csArticleCreateRequestDTO.setRegip(request.getRemoteAddr());
        //NOTI:자동적으로 rdate를 넣지 못해서 rdate를 임의로 추가해줌
        //NOTI:조금더 좋은 방법이 있을 것 같음
        csArticleCreateRequestDTO.setRdate(LocalDateTime.now());
        csArticleService.updateArticle(csArticleCreateRequestDTO);

        //view로 보내기 위한 articleId값 추가
        int articleId = csArticleCreateRequestDTO.getArticleId();


      return "redirect:/admin/cs/notice/view?articleId="+articleId;
    };

    @GetMapping("/notice/delete")
    public String notice_delete(@RequestParam("articleId") int articleId){
        csArticleService.deleteArticle(articleId);
        return "redirect:/admin/cs/notice/list";
    };

    @PostMapping("/notice/delete")
    public String notice_delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String cur;
        while ((cur = br.readLine()) != null) {
            sb.append(cur);
        }

        ObjectMapper om = new ObjectMapper();
        //json 요청을 받아와서 자바 객체로 포팅
        CsArticleMultiDeleteRequest dto = om.readValue(sb.toString(), CsArticleMultiDeleteRequest.class);
        for(Integer id: dto.getArticleIds()){
            csArticleService.deleteArticle(id);
        }
        return "redirect:/admin/cs/notice/list";
    };


/////////////////////////////////////////
////////////////////////////////////////
/*  admin/cs/qna  */
////////////////////////////////////////
///////////////////////////////////////
    @GetMapping("/qna/list")
    public String qna_list(){
        return "admin/cs/qna/list";
    }

/////////////////////////////////////////
////////////////////////////////////////
/*  admin/cs/faq  */
////////////////////////////////////////
///////////////////////////////////////
    @GetMapping("/faq/list")
    public String faq_list(){
        return "admin/cs/faq/list";
    }
}
