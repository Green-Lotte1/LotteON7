package kr.co.lotteon.service.admin.product;

import kr.co.lotteon.repository.admin.product.ProductCate1Repository;
import kr.co.lotteon.repository.admin.product.ProductCate2Repository;
import kr.co.lotteon.repository.admin.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductAdminService {
    private final ProductRepository productRepository;
    private final ProductCate1Repository cate1Repository;
    private final ProductCate2Repository cate2Repository;
}
