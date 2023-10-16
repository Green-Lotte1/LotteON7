package kr.co.lotteon.service.product;

import kr.co.lotteon.entity.product.ProductEntity;
import kr.co.lotteon.repository.product.ProductListRepository;
import kr.co.lotteon.response.product.ProductListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductListService {

    private final ProductListRepository productListRepository;

    public List<ProductListResponse> findByCate1AndCate2(int prodCate1, int prodCate2){

        return null;
    }

}
