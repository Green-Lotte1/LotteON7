package kr.co.lotteon.entity.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Id
    private int cate1;  // km_product_cate1 테이블 외래키 annotation 확인
    private int cate2;
    private String c2Name;
}
