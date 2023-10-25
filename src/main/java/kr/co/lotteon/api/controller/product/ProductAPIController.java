package kr.co.lotteon.api.controller.product;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.lotteon.dto.product.ProductDTO;
import kr.co.lotteon.request.product.ProductOrderItemRequest;
import kr.co.lotteon.response.admin.product.Cate1DictAPIResponse;
import kr.co.lotteon.response.product.ProductCartResponse;
import kr.co.lotteon.response.product.ProductOrderResponse;
import kr.co.lotteon.service.admin.product.CateAPIService;
import kr.co.lotteon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductAPIController {
    //카테고리 1차,2차 값 가져오기
    private final CateAPIService cateAPIService;
    private final ProductService productService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/fullCate")
    public List<Cate1DictAPIResponse> test() {
        return cateAPIService.getCateDictResponse();
    }

    /**
     * product 이미지를 요청 받으면 이미지를 준다.
     */
    @GetMapping("/images/{storedName}")
    public ResponseEntity<Resource> getProductImage(@PathVariable("storedName") String storedName) throws IOException {
        String imgPathStr = String.format("%s%s", fileDir, storedName);
        FileSystemResource imgResource = new FileSystemResource(imgPathStr);
        HttpHeaders httpHeaders = new HttpHeaders();

        //content-type 등록
        //Path imgPath = Paths.get(imgPathStr);
        //String contentType = Files.probeContentType(imgPath);
        httpHeaders.add("Content-Type", "image/jpeg");

        return new ResponseEntity<Resource>(imgResource, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/cart_to_order")
    public List<ProductDTO> cartToOrder(@RequestBody ProductOrderItemRequest productOrderItemRequest) {
        log.info("[CART TO ORDER] order request : {}", Arrays.toString(productOrderItemRequest.getProducts().stream().toArray()));

        return productOrderItemRequest.getProducts();
    }
}
