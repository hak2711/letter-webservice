package com.darling.dear.web;

import com.darling.dear.service.LetterService;
import com.darling.dear.web.dto.LetterSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LetterApiController {
    private final LetterService letterService;

    @PostMapping("/letters")
    public Long save(@RequestBody LetterSaveRequestDto requestDto){
        return letterService.save(requestDto);
    }
}
