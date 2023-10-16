package kr.co.lotteon.controller.admin.product;

import kr.co.lotteon.request.admin.product.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {
    @GetMapping("/register")
    public String productRegisterForm(@ModelAttribute("productInfo") ProductCreateRequest dto) {
        return "admin/product/register";
    }

    @PostMapping("/register")
    public String productRegister(@ModelAttribute("productInfo") ProductCreateRequest dto) {
        return null;
    }
}
