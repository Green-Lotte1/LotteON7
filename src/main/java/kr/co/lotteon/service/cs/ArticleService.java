package kr.co.lotteon.service.cs;

import kr.co.lotteon.entity.cs.ArticleEntity;
import kr.co.lotteon.repository.cs.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<ArticleEntity> getNotices() {
        return articleRepository.findArticlesByCate("notice");
    }

}
