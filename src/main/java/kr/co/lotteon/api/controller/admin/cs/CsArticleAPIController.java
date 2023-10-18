package kr.co.lotteon.api.controller.admin.cs;


import kr.co.lotteon.response.admin.cs.CsArticleCateAPIResponse;
import kr.co.lotteon.service.admin.cs.CsArticleAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/cs")
public class CsArticleAPIController {

    private final CsArticleAPIService csArticleAPIService;

    @GetMapping("/article/fullCate")
    public CsArticleCateAPIResponse test(){
        return csArticleAPIService.getArticleCate();
    }

}
