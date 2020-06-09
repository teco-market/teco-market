package com.teco.market.generation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.teco.market.BaseEntity;
import com.teco.market.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Generation extends BaseEntity {

    @OneToMany(mappedBy = "generation")
    private List<Member> members = new ArrayList<>();
    private String alias;

    public void addMember(Member member) {
        members.add(member);
    }
}
