package kr.co.lotteon.response.admin.product;

import kr.co.lotteon.entity.product.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ProductAdminListResponse {
    private String thumb1StoredName;
    private int prodNo;
    private String prodName;
    private int price;
    private int discount;
    private int point;
    private int stock;
    private String sellerName;
    private int hit;

    @Builder
    public ProductAdminListResponse(ProductEntity product){
        this.thumb1StoredName = product.getThumb1().getStoredFileName();
        this.prodNo = product.getProdNo();
        this.prodName = product.getProdName();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.point = product.getPoint();
        this.stock = product.getStock();
        this.sellerName = product.getSeller().getName();
        this.hit = product.getHit();
    }
}
