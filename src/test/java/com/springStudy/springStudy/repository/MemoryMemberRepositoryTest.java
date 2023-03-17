package com.springStudy.springStudy.repository;

import com.springStudy.springStudy.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 모든 테스트는 메소드의 순서를 고려하지 않고 진행됨 ! 따라서 각 테스트마다 아래의 코드로 초기화 해주어야 함
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        // given
        Member member = new Member();
        member.setName("Simon");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); // 둘이 동일한지를 확인함
    }

    @Test
    public void findByName(){
        // given
        Member member1 = new Member();
        member1.setName("Hello");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("World");
        repository.save(member2);

        // when
        Member result = repository.findByName("Hello").get();

        // then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        // given
        Member member1 = new Member();
        member1.setName("Hello");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("World");
        repository.save(member2);

        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }
}