package kr.co.lotteon.response.product;

import kr.co.lotteon.entity.product.ProductCate2Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class ProductCate2Response {
    private int cate1;
    private int cate2;
    private String c2Name;

    public ProductCate2Entity toEntity(){
        return ProductCate2Entity.builder()
                .cate1(cate1)
                .cate2(cate2)
                .c2Name(c2Name)
                .build();
    }
}
