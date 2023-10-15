package kr.co.lotteon.controller.product;

import kr.co.lotteon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product") // NOTI: 이런식으로 루트 url을 파주고 진행하면 좋다.
public class CartController {
    //NOTI: DI(dependancy injection)는 필드주입이 아니라 생성자 주입으로
    private final MemberRepository memberRepository;
    // 카트
    @GetMapping("/product/cart")
    public String cart(){
        return "product/cart";
    }
}
