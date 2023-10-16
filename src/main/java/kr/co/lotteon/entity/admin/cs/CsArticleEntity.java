package kr.co.lotteon.entity.admin.cs;

import jakarta.persistence.*;
import kr.co.lotteon.request.admin.cs.CsArticleCreateRequest;
import kr.co.lotteon.response.admin.cs.CsArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="km_article")
public class CsArticleEntity {
// NOTI: 스토리보드에 file항목이 없으므로 km_article에서 file 항목 삭제

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int article_id;

    private String cate;
    private String menu1;
    private String menu2;
    private String title;
    private String content;
    private int hit;
    private String uid;

    private String regip;

    @CreationTimestamp
    private LocalDateTime rdate;

    public CsArticleCreateRequest reqToEntity(){
        return CsArticleCreateRequest.builder()
                .article_id(article_id)
                .cate(cate)
                .menu1(menu1)
                .menu2(menu2)
                .title(title)
                .content(content)
                .hit(hit)
                .uid(uid)
                .regip(regip)
                .rdate(rdate)
                .build();
    }

    public CsArticleResponse respToEntity(){
        return CsArticleResponse.builder()
                .article_id(article_id)
                .cate(cate)
                .menu1(menu1)
                .menu2(menu2)
                .title(title)
                .content(content)
                .hit(hit)
                .uid(uid)
                .regip(regip)
                .rdate(rdate)
                .build();
    }

}
