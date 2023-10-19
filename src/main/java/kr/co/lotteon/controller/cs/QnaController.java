package kr.co.lotteon.controller.cs;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.controller.dto.ArticleDTO;
import kr.co.lotteon.entity.cs.ArticleEntity;
import kr.co.lotteon.service.cs.ArticleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    ArticleService articleService;

    @GetMapping(value = "/list")
    public String list(Model model){
        List<ArticleEntity> qnaList = articleService.getNotices();
        model.addAttribute("qnaList", qnaList);
        return "cs/qna/list";
    }

    @GetMapping("/view")
    public String view(){
        return "cs/qna/view";
    }

    @GetMapping("/write")
    public String writeView(){
        return "cs/qna/write";
    }

    @PostMapping("/write")
    public String write(HttpServletRequest request, ArticleDTO dto){
        dto.setRegip(request.getRemoteAddr());
        log.info("qna write : " + dto.toString());
        articleService.save(dto);
        return "redirect:/qna/list";
    }
}
