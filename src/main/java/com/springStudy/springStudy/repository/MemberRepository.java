package com.springStudy.springStudy.repository;

import com.springStudy.springStudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(long id); // NPE(NullPointerException)를 방지하기 위해 Optional 사용함

    Optional<Member> findByName(String name); // NPE(NullPointerException)를 방지하기 위해 Optional 사용함

    List<Member> findAll();
}
