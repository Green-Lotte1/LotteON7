package kr.co.lotteon.controller.product;

import kr.co.lotteon.response.product.ProductCate1Response;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.service.product.CateService;
import kr.co.lotteon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final CateService cateService;
    private final ProductService productService;

    /* Category */
    @GetMapping("/cate")
    public String categories(Model model){
       log.info("ProductController categories 시작");
        List<ProductCate1Response> categories = cateService.getCate();
        log.info("ProductController categories : " + categories);
        model.addAttribute("categories",categories);
        return "cate";
    }

    /* Product List */
    @GetMapping("/list/{prodCate2Id}")
    public String list(@PathVariable("prodCate2Id") Integer prodCate2Id, Model model){
        List<ProductListResponse> products = productService.findProductsByCate2(prodCate2Id);

        model.addAttribute("products",products);
        return "product/list";
    }

    /* Product View */
    @GetMapping("/view")
    public String view(){
        return "product/view";
    }

    /* Product Cart */
    @GetMapping("/cart")
    public String cart(){
        return "product/cart";
    }

    /* Product Order */
    @GetMapping("/order")
    public String order(){
        return "product/order";
    }

    /* Product Complete */
    @GetMapping("/complete")
    public String complete(){
        return "product/complete";
    }

    /* Product Search */
    @GetMapping("/search")
    public String search(){
        return "product/search";
    }
}
