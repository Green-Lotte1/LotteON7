package kr.co.lotteon.service.product;

import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.repository.product.ProductListRepository;
import kr.co.lotteon.repository.product.ProductViewRepository;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.response.product.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductListRepository productListRepository;
    private final ProductViewRepository productViewRepository;
    private final ModelMapper modelMapper;

    public List<ProductListResponse> findProductsByCate2(Integer prodCate2Id){


        List<ProductListResponse> productList = productListRepository.findByCate2(prodCate2Id).stream()
                .map(ProductListResponse::new)
                .collect(Collectors.toList());

        log.info("productList : " + productList);
        return productList;
    }

    //ProductView
    public ProductViewResponse findView(Integer prodNo){
        ProductEntity productEntity = productViewRepository.findById(prodNo).orElseThrow();
        return new ProductViewResponse(productEntity);
    }
}

