package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;
    private String name;

    @ManyToMany     // Category도 List로 아이템을 참조하고, 아이템도 List로 카테고리를 가짐
    @JoinTable(name="category_item",
            joinColumns= @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")) // 다대다를 풀어내는 방법, 실무에서는 쓰지 말 것. 필드 추가 불가능하므로.. JPA에서 되기는 함
    private List<Item> child = new ArrayList<>();
}
