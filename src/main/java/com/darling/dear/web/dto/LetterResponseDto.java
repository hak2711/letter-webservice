package com.darling.dear.web.dto;

import com.darling.dear.domain.letter.Letter;
import com.darling.dear.domain.user.User;
import lombok.Getter;

@Getter
public class LetterResponseDto {
    private String title;
    private String content;
    private String alias;

    public LetterResponseDto(Letter entity){
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.alias = entity.getAlias();
    }
}
