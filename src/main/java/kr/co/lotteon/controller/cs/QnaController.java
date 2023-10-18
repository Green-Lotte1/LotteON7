package kr.co.lotteon.controller.cs;

import kr.co.lotteon.controller.dto.ArticleDTO;
import kr.co.lotteon.entity.cs.ArticleEntity;
import kr.co.lotteon.service.cs.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
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
    public String write(){
        return "cs/qna/write";
    }

    @PostMapping("/write")
    public String write(ArticleDTO dto){
        articleService.save(dto);
        return "redirect:cs/qna/list";
    }
}
