package kr.co.lotteon.service.admin.cs;


import kr.co.lotteon.entity.admin.cs.CsArticleEntity;
import kr.co.lotteon.repository.admin.cs.CsArticleRepository;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequestDTO;
import kr.co.lotteon.request.admin.cs.CsArticlePageRequestDTO;
import kr.co.lotteon.response.admin.cs.CsArticlePageResponseDTO;
import kr.co.lotteon.response.admin.cs.CsArticleResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class CsArticleService {

    private final CsArticleRepository csArticleRepository;
    private final ModelMapper modelMapper;



    public void save(CsArticleCreateRequestDTO csArticleCreateRequestDTO){
    log.info("sevice ArticleCreateRequest :"+ csArticleCreateRequestDTO.toString());

    CsArticleEntity csArticleEntity =  csArticleCreateRequestDTO.toEntity();
    log.info("Service Entity: "+csArticleEntity.toString());
    csArticleRepository.save(csArticleEntity);
    }

    /*
    public List<CsArticleResponseDTO> findAll(){
        List<CsArticleEntity>  listCsArticleEntity = csArticleRepository.findAll();
        List<CsArticleResponseDTO> csArticleResponses = new ArrayList<>();

        for(CsArticleEntity article : listCsArticleEntity){
            CsArticleResponseDTO resp =  article.respToEntity();
            csArticleResponses.add(resp);
        }

        return csArticleResponses;
    }
*/

    public CsArticlePageResponseDTO findByCate(CsArticlePageRequestDTO pageRequestDTO){

        //getPageable(  ) 안에 넣는 변수에 따라서 내림차순 정렬이 된다.
        //getPageable(  ) 안에 cate를 넣었는데 다 똑같으므로 내림차순 정렬이 되지 않았다.
        Pageable pageable = pageRequestDTO.getPageable("rdate");

        Page<CsArticleEntity> result = csArticleRepository.findByCate(pageRequestDTO.getCate(),pageable);

        List<CsArticleResponseDTO> dtoList = result.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, CsArticleResponseDTO.class))
                .toList();

        int totalElement = (int) result.getTotalElements();

        return  CsArticlePageResponseDTO.builder()
                .csArticlePageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElement)
                .build();
    };




}
