package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_product_cate1") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductCate1Entity {

    @Id
    private int cate1;
    private String c1Name;
}
