package com.springStudy.springStudy.service;

import com.springStudy.springStudy.domain.Member;
import com.springStudy.springStudy.repository.MemberRepository;
import com.springStudy.springStudy.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired // 생성자에 Autowired를 적용하여 구현한 dependency injection
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository; // dependency injection
    }

    // 회원 가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // Optional 객체를 사용할 경우 쓸 수 있는 ifPresent 메소드
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
