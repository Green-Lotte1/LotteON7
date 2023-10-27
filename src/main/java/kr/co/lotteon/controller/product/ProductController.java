package kr.co.lotteon.controller.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.request.admin.cs.CsArticleMultiDeleteRequest;
import kr.co.lotteon.request.product.ProductCartRequest;
import kr.co.lotteon.request.product.ProductOrderItemRequest;
import kr.co.lotteon.request.product.ProductSearchFieldRequest2;
import kr.co.lotteon.response.admin.product.PageInfoResponse;
import kr.co.lotteon.response.product.ProductCartResponse;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.response.product.ProductViewResponse;
import kr.co.lotteon.service.member.MemberService;
import kr.co.lotteon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;

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
    public String cart(@PathVariable("uid") String uid, Model model) throws JsonProcessingException {
        // Cart Select
        List<ProductCartResponse> productCarts = productService.findCart(uid);
        model.addAttribute("productCarts",productCarts);

//        // Cart Delete
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<ProductCartResponse> productCartResponses = objectMapper.readValue(jsonData, new TypeReference<List<ProductCartResponse>>() {});
//        productService.delete

        return "product/cart";
    }
//    @GetMapping("/cart")
//    public String cart(@RequestParam("jsonData")String jsonData ) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<ProductCartResponse> productCartResponses = objectMapper.readValue(jsonData, new TypeReference<List<ProductCartResponse>>() {});
//
//        return "redirect:/product/cart";
//    }

    @PostMapping("/cart/{uid}")
    public   String cart(@PathVariable("uid") String uid, ProductCartRequest productCartRequest) throws IOException {

        productService.insertCart(productCartRequest);
        log.info("postCart uid" + uid);

        return "redirect:/product/cart/{uid}";
    }

    @PostMapping("/cart/delete/{uid}")
    public   String deleteCart(@PathVariable("uid") String uid,HttpServletRequest req, HttpServletResponse resp) throws IOException {

        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String cur;
        while ((cur = br.readLine()) != null) {
            sb.append(cur);
        }

        ObjectMapper om = new ObjectMapper();
        //json 요청을 받아와서 자바 객체로 포팅
        CsArticleMultiDeleteRequest dto = om.readValue(sb.toString(), CsArticleMultiDeleteRequest.class);
//        for(Integer id: dto.getArticleIds()){
//            csArticleService.deleteArticle(id);
//        }

        log.info("productController deleteCart uid : " +uid);
        return null;
//        return "redirect:/cart/{uid}";
    }

//    @PostMapping("/notice/delete")
//    public String notice_delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        BufferedReader br = req.getReader();
//        StringBuilder sb = new StringBuilder();
//        String cur;
//        while ((cur = br.readLine()) != null) {
//            sb.append(cur);
//        }
//
//        ObjectMapper om = new ObjectMapper();
//        //json 요청을 받아와서 자바 객체로 포팅
//        CsArticleMultiDeleteRequest dto = om.readValue(sb.toString(), CsArticleMultiDeleteRequest.class);
//        for(Integer id: dto.getArticleIds()){
//            csArticleService.deleteArticle(id);
//        }
//        return "redirect:/admin/cs/notice/list";
//    };

    /* Product Order */
    @GetMapping("/order")
    public String order(@RequestParam("jsonData")String jsonData, Model model ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Member uid = null;
        List<ProductCartResponse> productOrderCarts = objectMapper.readValue(jsonData, new TypeReference<List<ProductCartResponse>>() {});
        List<ProductCartResponse> productOrderList = new ArrayList<>();
        for(ProductCartResponse productCartResponse : productOrderCarts){
            log.info("cartNo : "+productCartResponse.getCartNo());
            if(productCartResponse.getCartNo() != 0){
                ProductCartResponse response = productService.productOrderList(productCartResponse.getCartNo());
                log.info("response :"+response);
                uid =response.getUid();

                productOrderList.add(response);
            }

        }
        assert uid != null;
         Member orderUser =  memberService.findMember(uid);
         log.info("orderUser :"+orderUser.toString());
        log.info("orderLists : "+productOrderList.toString());
        model.addAttribute("orderLists",productOrderList);
        model.addAttribute("orderUser",orderUser);
        return "product/order";
    }

    @PostMapping ("/order")
    public String order(@RequestBody String formInfo, ProductOrderItemRequest productOrderItemRequest ){

//            log.info("productController PostMapping /product/order : "+products);


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
    public String search(Model model, @ModelAttribute("searchCondField2")ProductSearchFieldRequest2 searchCondField2, @PageableDefault(sort = "prodNo", direction = Sort.Direction.DESC,size = 10)Pageable pageable){
        log.info("[PRODUCT SEARCH LIST] searchCond : {}",searchCondField2);
        Page<ProductListResponse> results = productService.getPagedProductsWithConds(searchCondField2.toSearchCond(),pageable);
        List<ProductListResponse> products = results.getContent();

        model.addAttribute("products",products);
        log.info("[PRODUCT LIST] page info : {}", new PageInfoResponse(results.getTotalElements(), pageable));
        model.addAttribute("pageInfo", new PageInfoResponse(results.getTotalElements(),pageable));

        return "product/search";
    }
}