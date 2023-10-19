package kr.co.lotteon.request.product;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCartEntity;
import kr.co.lotteon.entity.product.ProductEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ProductCartRequest {

    String uid;
    int prodNo;
    String prodName;
    String descript;
    int num;
    int price;
    int discount;
    int point;
    int delivery;
    String thumb2;
    int total;

    public ProductCartEntity toEntity(ProductEntity prodNo, Member uid){
        return ProductCartEntity.builder()
                .uid(uid) // Member를 참조 중 확인!!
                .prodNo(prodNo) // productEntity 참조중
                .count(num) // DB 컬럼명 : `count` , request data : num
                .price(price)
                .discount(discount)
                .point(point)
                .delivery(delivery)
                .total(total)
                .build();

    }

}
