package kr.co.lotteon.repository.main;


import kr.co.lotteon.entity.product.ProductCate1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cate1Repository extends JpaRepository<ProductCate1Entity, Integer> {
}
