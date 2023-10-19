package kr.co.lotteon.service.product;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.repository.admin.product.ProductRepository;
import kr.co.lotteon.repository.member.MemberRepository;
import kr.co.lotteon.repository.product.ProductCartRepository;
import kr.co.lotteon.repository.product.ProductListRepository;
import kr.co.lotteon.repository.product.ProductViewRepository;
import kr.co.lotteon.request.product.ProductCartRequest;
import kr.co.lotteon.response.product.ProductListResponse;
import kr.co.lotteon.response.product.ProductViewResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductListRepository productListRepository;
    private final ProductViewRepository productViewRepository;
    private final ProductCartRepository productCartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    /* Product List */
    public List<ProductListResponse> findProductsByCate2(Integer prodCate2Id){


        List<ProductListResponse> productList = productListRepository.findByCate2(prodCate2Id).stream()
                .map(ProductListResponse::new)
                .collect(Collectors.toList());

        log.info("productList : " + productList);
        return productList;
    }

    /* Product View */
    public ProductViewResponse findView(Integer prodNo){
        ProductEntity productEntity = productViewRepository.findById(prodNo).orElseThrow();
        return new ProductViewResponse(productEntity);
    }

    /* Product Cart */
    public void insertCart(ProductCartRequest productCartRequest) throws IOException {
        ProductEntity prodNo = productRepository.findById(productCartRequest.getProdNo()).orElseThrow();
        Member uid = memberRepository.findById(productCartRequest.getUid()).orElseThrow();
        productCartRepository.save(productCartRequest.toEntity(prodNo, uid));
    }
}

