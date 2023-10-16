package kr.co.lotteon.repository.product;

import kr.co.lotteon.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<ProductEntity,Integer> {
    public List<ProductEntity> findByCate1AndCate2(int prodCate1, int prodCate2);
}
