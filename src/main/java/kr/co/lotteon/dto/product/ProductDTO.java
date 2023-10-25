package kr.co.lotteon.dto.product;

import kr.co.lotteon.entity.product.ProductOrderItemEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    // OrderItem insert
    private int prodNo;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;

    private String uid;
    private String prodName;
    private String descript;
    private String thumb1;

    public ProductOrderItemEntity toOrderItem() {
        return ProductOrderItemEntity.builder()
                .prodNo(prodNo)
                .count(count)
                .price(price)
                .discount(discount)
                .point(point)
                .delivery(delivery)
                .total(total)
                .build();
    }
}
