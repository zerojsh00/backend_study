package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    /*
    * [EntityManager 설정은 스프링부트가 자동으로 만듦 !]
    *
    * build.gradle 파일에	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'를 입력하면
    * 스프링부트가 알아서 application.yml 파일의 설정들을 참고하여 EntityManager를 생성함
    *
     * */
    @PersistenceContext // 스프링부트가 해당 어노테이션이 있으면 EntityManager를 주입해줌
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        /* TIP
         * member를 반환하지 않고 id만 반환함
         * 저장하는 기능은 커맨드이므로, 리턴값을 안 만드는 것이 좋다?
         * 커맨드와 쿼리를 분리하는 설계!
         * id 값정도를 반환하면 나중에 조회하기 편하므로 이 정도는 반환함
         * */
        return member.getId();
    }
    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
