package com.springStudy.springStudy.repository;

import com.springStudy.springStudy.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaMemberRepository implements MemberRepository{

/*
    JPA는 EntityManager를 이용하여 동작함
    EntityManager는 영속 컨텍스트에 접근하여 엔티티에 대한 DB 작업을 제공함

    build.gradle에서
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    명령으로 설정을 하면, 스프링부트가 자동으로 현재 application.properties 내용과 DB내용 등이 연동된 EntityManager를 만들어 줌

    이것을 injection하여 사용함
*/

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member){
        em.persist(member); // 영구 저장하기. 이렇게만 하면 insert query를 만들어서 DB에 저장까지 함
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name){
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
