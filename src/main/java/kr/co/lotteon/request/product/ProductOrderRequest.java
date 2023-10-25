package kr.co.lotteon.request.product;

import lombok.Data;

@Data
public class ProductOrderRequest {

    String uid;
    int prodNo;
    String prodName;
    String descript;
    int count;
    int price;
    int discount;
    int point;
    int delivery;
    String thumb1;
    int total;
}
