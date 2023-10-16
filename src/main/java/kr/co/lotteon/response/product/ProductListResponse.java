package kr.co.lotteon.response.product;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCate1Entity;
import kr.co.lotteon.entity.product.ProductCate2Entity;
import kr.co.lotteon.entity.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class ProductListResponse {

    private int prodNo;
    private String prodName;
    private String descript;
    private String seller;
    private String level;
    private int price;
    private int discount;   // default : 0
    private int delivery;   // default : 0
    private int score;
    private String thumb1;

    public ProductListResponse(ProductEntity product) {
        this.prodNo = product.getProdNo();
        this.prodName = product.getProdName();
        this.descript = product.getDescript();
        this.seller = product.getSeller().getUid();
        this.level = product.getSeller().getLevel().name();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.delivery = product.getDelivery();
        this.score = product.getScore();
        this.thumb1 = product.getThumb1();

        log.info("prodNo : "+prodNo);
        log.info("prodName : " + prodName);
        log.info("descript : " + descript);
        log.info("seller : " + seller);
        log.info("price : " + price);
        log.info("discount : " + discount);
        log.info("delivery : " + delivery);
        log.info("thumb1 : " + thumb1);
    }
}
