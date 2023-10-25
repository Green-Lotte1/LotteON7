package kr.co.lotteon.repository.admin.product.presentation;

import lombok.*;

@Getter @Setter
@ToString(of = {"prodName","prodNo","company","sellerName"})
@NoArgsConstructor
public class ProductSearchCond {
    private String prodName;
    private Integer prodNo;
    private String company;
    private String sellerName;
    private Integer pageSize;

    @Builder
    public ProductSearchCond(String prodName, Integer prodNo, String company, String sellerName,Integer pageSize) {
        this.prodName = prodName;
        this.prodNo = prodNo;
        this.company = company;
        this.sellerName = sellerName;
        this.pageSize = pageSize;
    }
}
