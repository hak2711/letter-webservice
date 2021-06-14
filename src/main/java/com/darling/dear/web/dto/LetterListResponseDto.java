package com.darling.dear.web.dto;

import com.darling.dear.domain.letter.Letter;
import com.darling.dear.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LetterListResponseDto {
    private String title;
    private LocalDateTime createdDate;

    public LetterListResponseDto(Letter entity){
        this.title = entity.getTitle();
        this.createdDate = entity.getCreatedDate();
    }
}
