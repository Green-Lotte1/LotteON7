package kr.co.lotteon.repository.main;

import kr.co.lotteon.entity.product.ProductCate1Entity;
import kr.co.lotteon.entity.product.ProductCate2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Cate2Repository extends JpaRepository<ProductCate2Entity, String> {
    List<ProductCate2Entity> findByCate1(ProductCate1Entity productCate1Entity);
}
