package kr.co.lotteon.service.admin.cs;


import kr.co.lotteon.entity.admin.cs.CsArticleEntity;
import kr.co.lotteon.repository.admin.cs.CsArticleRepository;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequest;
import kr.co.lotteon.response.admin.cs.CsArticleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CsArticleService {

    private final CsArticleRepository csArticleRepository;

    public void save(CsArticleCreateRequest csArticleCreateRequest){
    log.info("sevice ArticleCreateRequest :"+csArticleCreateRequest.toString());

    CsArticleEntity csArticleEntity =  csArticleCreateRequest.toEntity();
    log.info("Service Entity: "+csArticleEntity.toString());
    csArticleRepository.save(csArticleEntity);
    }

    public List<CsArticleResponse> findAll(){
        List<CsArticleEntity>  listCsArticleEntity = csArticleRepository.findAll();
        List<CsArticleResponse> csArticleResponses = new ArrayList<>();

        for(CsArticleEntity article : listCsArticleEntity){
            CsArticleResponse resp =  article.respToEntity();
            csArticleResponses.add(resp);
        }

        return csArticleResponses;
    }
}
