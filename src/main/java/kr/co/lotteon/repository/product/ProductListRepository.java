package kr.co.lotteon.repository.product;

import kr.co.lotteon.entity.product.ProductEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductListRepository extends JpaRepository<ProductEntity,Integer> {


    @Query("select p from ProductEntity p join fetch p.seller s join fetch p.prodCate2 ct where ct.cate2 = :cate2 order by p.rdate desc ,p.prodNo desc ")
    public List<ProductEntity> findByCate2(@Param("cate2") Integer cate2);
}
