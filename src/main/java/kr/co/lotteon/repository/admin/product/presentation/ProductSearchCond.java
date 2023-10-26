package kr.co.lotteon.repository.admin.product.presentation;

import lombok.*;

@Getter @Setter
@ToString(of = {"prodName","prodNo","company","sellerName"})
@NoArgsConstructor
public class ProductSearchCond {
    private String prodName;
    private String descript;
    private Integer prodNo;
    private String company;
    private String sellerName;
    private Integer pageSize;

    @Builder
    public ProductSearchCond(String prodName, Integer prodNo, String company, String sellerName,Integer pageSize,String descript) {
        this.prodName = prodName;
        this.descript = descript;
        this.company = company;
        this.sellerName = sellerName;
        this.pageSize = pageSize;
        this.prodNo = prodNo;
    }
}
