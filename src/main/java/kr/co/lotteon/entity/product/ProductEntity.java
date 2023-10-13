package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_product") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodNo;
    private int prodCate1;  // km_product_cate2 테이블 외래키 annotation 확인
    private int prodCate2;
    private String prodName;
    private String descript;
    private String company;
    private String seller;
    private int price;
    private int discount;   // default : 0
    private int point;      // default : 0
    private int stock;      // default : 0
    private int sold;       // default : 0
    private int delivery;   // default : 0
    private int hit;        // default : 0
    private int score;      // default : 0
    private int review;     // default : 0
    private String thumb1;
    private String thumb2;
    private String thumb3;
    private String detail;
    private String status;  // default : '새상품'
    private String duty;    // default : '과세상품'
    private String receipt; // default : '발행가능 - 신용카드 전표, 온라인 현금영수증'
    private String bizType; // default : '사업자 판매자'
    private String origin;  // default : '상세설명참고'
    private String ip;
    @CreationTimestamp
    private LocalDateTime rdate;
}
