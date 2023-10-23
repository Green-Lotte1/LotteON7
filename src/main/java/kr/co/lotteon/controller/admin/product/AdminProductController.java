package kr.co.lotteon.controller.admin.product;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.repository.admin.product.presentation.ProductSearchCond;
import kr.co.lotteon.request.admin.product.ProductCreateRequest;
import kr.co.lotteon.response.admin.product.PageInfoResponse;
import kr.co.lotteon.response.admin.product.ProductAdminListResponse;
import kr.co.lotteon.service.admin.product.ProductAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {
    private final ProductAdminService productAdminService;

    @GetMapping("/register")
    public String productRegisterForm(@ModelAttribute("productInfo") ProductCreateRequest dto) {
        return "admin/product/register";
    }

    @PostMapping("/register")
    public String productRegister(@ModelAttribute("productInfo") ProductCreateRequest dto, HttpServletRequest request) throws IOException {
        dto.setIp(request.getRemoteAddr());
        productAdminService.registerProduct(dto);
        return "redirect:/admin/index";
    }

    @GetMapping("/list")
    public String products(Model model, @ModelAttribute("searchCond") ProductSearchCond searchCond, @PageableDefault(sort = "prodNo",direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("[ADMIN PRODUCT LIST] searchCond : {}",searchCond);
        Page<ProductAdminListResponse> results = productAdminService.getPagedProductsWithConds(searchCond, pageable);
        List<ProductAdminListResponse> products = results.getContent();

        model.addAttribute("products", products);
        log.info("[ADMIN PRODUCT LIST] page info : {}", new PageInfoResponse(results.getTotalElements(), pageable));
        model.addAttribute("pageInfo", new PageInfoResponse(results.getTotalElements(), pageable));
        return "admin/product/list";
    }
}
