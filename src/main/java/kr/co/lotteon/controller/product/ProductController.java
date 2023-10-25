package kr.co.lotteon.controller.product;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.request.product.ProductCartRequest;
import kr.co.lotteon.request.product.ProductOrderItemRequest;
import kr.co.lotteon.response.product.ProductCartResponse;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.response.product.ProductViewResponse;
import kr.co.lotteon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
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
    @GetMapping("/cart/{uid}")
    public String cart(@PathVariable("uid") String uid, Model model){
        List<ProductCartResponse> productCarts = productService.findCart(uid);
        model.addAttribute("productCarts",productCarts);
        return "product/cart";
    }

    @PostMapping("/cart/{uid}")
    public   String cart(@PathVariable("uid") String uid, ProductCartRequest productCartRequest) throws IOException {

        productService.insertCart(productCartRequest);
        log.info("postCart uid" + uid);

        return "redirect:/product/cart/{uid}";
    }

    /* Product Order */
    @GetMapping("/order")
    public String order(@Param("products") List<ProductDTO> products ){
          log.info("productController getting /product/order : "+products);

        return "product/order";
    }

    @PostMapping ("/order")
    public String order(@RequestBody String formInfo, ProductOrderItemRequest productOrderItemRequest, @RequestParam("products") List<ProductDTO> products ){

            log.info("productController PostMapping /product/order : "+products);


        log.info( "너는 누구?"+formInfo);

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