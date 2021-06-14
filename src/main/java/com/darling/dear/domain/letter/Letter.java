package com.darling.dear.domain.letter;

import com.darling.dear.domain.BaseTimeEntity;
import com.darling.dear.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Letter extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn
    private User sender;

    @ManyToOne
    @JoinColumn
    private User receiver;

    @Column(nullable = false)
    private String alias;

    @Builder
    public Letter(String title, String content, User sender, User receiver, String alias){
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.alias = alias;
    }
}
