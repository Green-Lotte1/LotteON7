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
@Table(name="km_product_cart") // 테이블 명 lotte_~ 로 바꿀지 의견 나눠보기
public class ProductCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartNo;
    private String uid; // member 테이블 외래키 annotation 확인
    private int prodNo; // product 테이블 외래키 annotation 확인
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;
    @CreationTimestamp
    private LocalDateTime rdate;
}