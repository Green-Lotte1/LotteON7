package kr.co.lotteon.api.controller.admin;

import kr.co.lotteon.response.admin.product.Cate1DictAPIResponse;
import kr.co.lotteon.service.admin.product.CateAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/product")
public class ProductAdminController {
    //카테고리 1차,2차 값 가져오기
    private final CateAPIService cateAPIService;
    @GetMapping("/test")
    public List<Cate1DictAPIResponse> test() {
        return cateAPIService.getCateDictResponse();
    }
}
