package kr.co.lotteon.response.product;

import kr.co.lotteon.entity.product.ProductEntity;
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
        this.thumb1 = product.getThumb1().getStoredFileName();

    }
}
