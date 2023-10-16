package kr.co.lotteon.repository.cs;

import kr.co.lotteon.entity.cs.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity,Integer> {
    public List<ArticleEntity> findArticlesByCate(String cate);
}
