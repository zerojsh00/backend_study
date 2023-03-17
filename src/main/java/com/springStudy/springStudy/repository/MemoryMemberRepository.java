package com.springStudy.springStudy.repository;

import com.springStudy.springStudy.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id)
    {
        // 만약 어떤 데이터가 null이 올 수도 있고 아닐 수도 있는 경우에는 Optional.ofNullbale로 생성함
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // findAny()는 Stream에서 가장 먼저 탐색되는 요소를 리턴
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
