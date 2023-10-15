package kr.co.lotteon.service.product;

import kr.co.lotteon.entity.product.ProductCate1Entity;
import kr.co.lotteon.entity.product.ProductCate2Entity;
import kr.co.lotteon.repository.product.Cate1Repository;
import kr.co.lotteon.repository.product.Cate2Repository;
import kr.co.lotteon.response.product.ProductCate1Response;
import kr.co.lotteon.response.product.ProductCate2Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CateService {

    private final Cate1Repository cate1Repository;
    private final Cate2Repository cate2Repository;

    public List<ProductCate1Response> getCate() {
        List<ProductCate1Entity> cate1EntityList = cate1Repository.findAll();
        log.info("cateService cate1EntityList : " + cate1EntityList.toString());

        List<ProductCate1Response> responseList = new ArrayList<>();
        log.info("cateService responseList : "+responseList.toString());

        for (ProductCate1Entity productCate1Entity : cate1EntityList){
            ProductCate1Response productCate1Response = productCate1Entity.toResponse();
            List<ProductCate2Entity> cate2EntityList = cate2Repository.findByCate1(productCate1Entity);
            List<ProductCate2Response> cate2ResponseList = new ArrayList<>();

            for (ProductCate2Entity productCate2Entity : cate2EntityList){
                cate2ResponseList.add(productCate2Entity.toResponse());
            }

            productCate1Response.setCate2List(cate2ResponseList);
            responseList.add(productCate1Response);
        }

        return responseList;
    }
}

