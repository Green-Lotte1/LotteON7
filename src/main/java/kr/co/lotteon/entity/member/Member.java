package kr.co.lotteon.entity.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Member {
    @Id
    private String uid;
    private String pass;
    private String name;
    @Enumerated(EnumType.STRING)
    private MemberGender gender;
    private String hp;
    @NotNull
    @Column(unique = true) //LEARN: unique 제약조건 column으로 지정
    private String email;
    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private Integer point;
    @Enumerated(EnumType.STRING)
    private MemberLevel level;
    private String zip;
    private String addr1;
    private String addr2;
    private String company;
    private String ceo;
    private String bizRegNum;
    private String comRegNum;
    private String tel;
    private String manager;
    private String managerHp;
    private String fax;
    private String regip; //TODO: 여러개의 ip를 받을 수 있도록 하여 다른 기기에서 로그인시 유효성 검사실시
    private LocalDateTime wdate;
    private LocalDateTime rdate;
    private Integer locationTerms;

}
