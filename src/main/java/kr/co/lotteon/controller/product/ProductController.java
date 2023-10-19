package kr.co.lotteon.controller.product;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.request.product.ProductCartRequest;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.response.product.ProductViewResponse;
import kr.co.lotteon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    /* Product List */
    @GetMapping("/list/{prodCate2Id}")
    public String list(@PathVariable("prodCate2Id") Integer prodCate2Id, Model model){
        List<ProductListResponse> products = productService.findProductsByCate2(prodCate2Id);

        model.addAttribute("products",products);
        return "product/list";
    }

    /* Product View */
    @GetMapping("/view/{prodNo}")
    public String view(@PathVariable("prodNo") Integer prodNo, Model model){
        ProductViewResponse productViewResponse = productService.findView(prodNo);
        log.info("ProductController : "+prodNo);
        model.addAttribute("product",productViewResponse);
        return "product/view";
    }

    /* Product Cart */
    @GetMapping("/cart")
    public String cart(){
        return "product/cart";
    }

    @PostMapping("/cart")
    @ResponseBody
    public String cart(ProductCartRequest productCartRequest, Model model) throws IOException {

        log.info(productCartRequest);

        productService.insertCart(productCartRequest);
        model.addAttribute("productCart", productCartRequest);

        return "redirect:product/cart";
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
