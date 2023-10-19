package kr.co.lotteon.request.admin.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CsArticlePageRequestDTO {

    @Builder.Default
    //인스턴스를 만들 때 특정 필드를 특정 값으로 초기화할 때 사용한다.
    private int pg = 1;

    @Builder.Default
    private int size = 10;

    private String cate;

    @Builder.Default
    private String menu1 = "0";

    @Builder.Default
    private String menu2 = "0";



    public Pageable getPageable(String sort){
        return PageRequest.of(this.pg-1, this.size, Sort.by(sort).descending());
    }


}
