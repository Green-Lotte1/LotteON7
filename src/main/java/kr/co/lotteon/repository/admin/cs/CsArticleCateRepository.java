package kr.co.lotteon.repository.admin.cs;

import kr.co.lotteon.entity.admin.cs.CsArticleCateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsArticleCateRepository  extends JpaRepository<CsArticleCateEntity,Integer> {
    @Query("select distinct a1 from CsArticleCateEntity a1 ")
    public List<CsArticleCateEntity> getArticleCate();
}
