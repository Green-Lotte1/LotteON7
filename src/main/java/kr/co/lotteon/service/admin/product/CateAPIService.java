package kr.co.lotteon.service.admin.product;

import kr.co.lotteon.entity.product.ProductCate1Entity;
import kr.co.lotteon.repository.product.Cate1Repository;
import kr.co.lotteon.repository.product.Cate2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 프로덕트 등록시 cate 선택할때 ajax로 요청받아
 * 카테고리 정보를 반환해주는 서비스
 * author: 이동한
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CateAPIService {
    private final Cate1Repository cate1Repository;
    private final Cate2Repository cate2Repository;

    public void getCateDictResponse() {
        List<ProductCate1Entity> results = cate1Repository.getCate1WithChildrenCate2();
        log.info("result = {}",results);
    }
}
