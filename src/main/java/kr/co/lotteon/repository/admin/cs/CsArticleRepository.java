package kr.co.lotteon.repository.admin.cs;

import kr.co.lotteon.entity.admin.cs.CsArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsArticleRepository extends JpaRepository<CsArticleEntity, Integer> {
}
