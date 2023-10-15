package kr.co.lotteon.service.member;

import kr.co.lotteon.entity.member.Member;
import kr.co.lotteon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member registerMember(Member not) {
        return null;
    }
}
