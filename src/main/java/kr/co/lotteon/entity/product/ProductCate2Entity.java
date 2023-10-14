package kr.co.lotteon.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kr.co.lotteon.response.main.ProductCate2Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_product_cate2") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductCate2Entity {

    private int cate1;  // km_product_cate1 테이블 외래키 annotation 확인
    @Id
    private int cate2;
    private String c2Name;

    public ProductCate2Response toResponse(){
        return ProductCate2Response.builder()
                .cate1(cate1)
                .cate2(cate2)
                .c2Name(c2Name)
                .build();
    }
}
