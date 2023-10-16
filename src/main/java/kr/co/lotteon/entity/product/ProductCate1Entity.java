package kr.co.lotteon.entity.product;

import jakarta.persistence.*;
import kr.co.lotteon.response.product.ProductCate1Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "prodCate1",cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    public void addProduct(ProductEntity productEntity) {
        this.products.add(productEntity);
        productEntity.setProdCate1(this);
    }
    public ProductCate1Response toResponse(){
        return ProductCate1Response.builder()
                .cate1(cate1)
                .c1Name(c1Name)
                .build();
    }
}
