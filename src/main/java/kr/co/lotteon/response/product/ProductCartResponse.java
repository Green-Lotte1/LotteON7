package kr.co.lotteon.response.product;

import kr.co.lotteon.entity.file.UploadFile;
import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.entity.product.ProductCartEntity;
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
public class ProductCartResponse {
    private Member uid;
    private int prodNo;
    private String prodName;
    private String descript;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;

    private String thumb1;

    public ProductCartResponse(ProductCartEntity productCartEntity) {
        this.uid = productCartEntity.getUid();
        this.prodNo = productCartEntity.getProduct().getProdNo();
        this.prodName = productCartEntity.getProduct().getProdName();
        this.descript = productCartEntity.getProduct().getDescript();
        this.count = productCartEntity.getCount();
        this.price = productCartEntity.getPrice();
        this.discount = productCartEntity.getDiscount();
        this.point = productCartEntity.getPoint();
        this.delivery = productCartEntity.getDelivery();
        this.total = productCartEntity.getTotal();
        this.thumb1 = productCartEntity.getProduct().getThumb1().getStoredFileName();

    }
}
