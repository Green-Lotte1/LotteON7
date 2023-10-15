package kr.co.lotteon.response.product;

import kr.co.lotteon.entity.product.ProductCate1Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class ProductCate1Response {
    private int cate1;
    private String c1Name;
    private List<ProductCate2Response> cate2List;

    public ProductCate1Entity toEntity(){
        return ProductCate1Entity.builder()
                .cate1(cate1)
                .c1Name(c1Name)
                .build();
    }
}