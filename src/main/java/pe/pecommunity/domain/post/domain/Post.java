package pe.pecommunity.domain.post.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pe.pecommunity.domain.BaseTimeEntity;
import pe.pecommunity.domain.File.domain.File;
import pe.pecommunity.domain.board.domain.Board;
import pe.pecommunity.domain.comment.domain.Comment;
import pe.pecommunity.domain.member.domain.Member;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_pk")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Long viewCnt;

    @Builder(builderMethodName = "createPostBuilder")
    public Post(Board board, Member member, String title, String content) {
        this.board = board;
        this.member = member;
        this.title = title;
        this.content = content;
        this.viewCnt = 0L;

        //==연관관계 편의 메서드==//
        board.getPosts().add(this);
        member.getPosts().add(this);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
