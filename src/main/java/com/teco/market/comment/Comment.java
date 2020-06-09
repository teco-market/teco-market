package com.teco.market.comment;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.teco.market.BaseEntity;
import com.teco.market.member.Member;
import com.teco.market.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
public class Comment extends BaseEntity {

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @Builder
    public Comment(String content, Member member, Post post) {
        this.content = content;
        this.member = member;
        this.post = post;
    }

    public boolean isWrittenBy(Member member) {
        return Objects.equals(this.member.getId(), member.getId());
    }

    public boolean isNotWrittenBy(Member member) {
        return !isWrittenBy(member);
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
