package kr.co.lotteon.service.product;

import kr.co.lotteon.entity.product.ProductCate1Entity;
import kr.co.lotteon.entity.product.ProductCate2Entity;
import kr.co.lotteon.repository.product.Cate1Repository;
import kr.co.lotteon.repository.product.Cate2Repository;
import kr.co.lotteon.response.main.ProductCate1Response;
import kr.co.lotteon.response.main.ProductCate2Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CateService {

    @Autowired
    private Cate1Repository cate1Repository;
    @Autowired
    private Cate2Repository cate2Repository;

    public List<ProductCate1Response> findAllCate1() {
        List<ProductCate1Entity> cate1EntityList = cate1Repository.findAll();
        List<ProductCate1Response> cate1ResponseList = cate1EntityList.stream()
                .map(ProductCate1Entity :: toResponse)
                .collect(Collectors.toList());
        return cate1ResponseList;
    }

    public List<ProductCate2Response> findAllCate2() {
        List<ProductCate2Entity> cate2EntityList = cate2Repository.findAll();
        List<ProductCate2Response> cate2ResponseList = cate2EntityList.stream()
                .map(ProductCate2Entity :: toResponse)
                .collect(Collectors.toList());
        return cate2ResponseList;
    }

    public List<ProductCate2Response> findByCate1(int cate1) {
        ProductCate1Entity productCate1Entity =  cate1Repository.findById(cate1).orElse(null);
        return null;
    }


}
