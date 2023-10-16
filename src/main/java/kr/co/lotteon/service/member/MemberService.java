package kr.co.lotteon.service.member;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member registerMember(Member preMember) {
        preMember.setPass(passwordEncoder.encode(preMember.getPass()));
        return memberRepository.save(preMember);
    }
}
