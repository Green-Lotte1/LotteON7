package kr.co.lotteon.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberLoginRequest {
    private String uid;
    private String pass;
}
