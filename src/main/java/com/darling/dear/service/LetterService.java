package com.darling.dear.service;

import com.darling.dear.domain.letter.Letter;
import com.darling.dear.domain.letter.LetterRepository;
import com.darling.dear.web.dto.LetterSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LetterService {
    private final LetterRepository letterRepository;

    @Transactional
    public Long save(LetterSaveRequestDto requestDto) {
        return letterRepository.save(requestDto.toEntity()).getId();
    }
}
