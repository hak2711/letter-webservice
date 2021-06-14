package com.darling.dear.web.dto;

import com.darling.dear.domain.letter.Letter;
import com.darling.dear.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LetterSaveRequestDto {
    private String title;
    private String content;
    private User sender;
    private User receiver;
    private String alias;

    @Builder
    public LetterSaveRequestDto(String title, String content, User sender, User receiver, String alias){
        this.title = title;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.alias = alias;
    }

    public Letter toEntity(){
        return Letter.builder()
                .title(title)
                .content(content)
                .sender(sender)
                .receiver(receiver)
                .alias(alias)
                .build();
    }
}
