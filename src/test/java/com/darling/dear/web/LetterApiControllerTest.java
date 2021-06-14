package com.darling.dear.web;

import com.darling.dear.domain.letter.Letter;
import com.darling.dear.domain.letter.LetterRepository;
import com.darling.dear.domain.user.User;
import com.darling.dear.service.LetterService;
import com.darling.dear.web.dto.LetterSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LetterApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private User sender;

    private User receiver;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        sender = User.builder()
                .name("sendId")
                .email("sendEmail")
                .build();
        receiver = User.builder()
                .name("receiveId")
                .email("receiveEmail")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        letterRepository.deleteAll();
    }

    @Test
    @WithMockUser
    public void letter_등록된다() throws Exception{
        //given
        String title = "title";
        String content = "content";
        String alias = "alias";
        LetterSaveRequestDto requestDto = LetterSaveRequestDto.builder()
                .title(title)
                .title(content)
                .sender(sender)
                .receiver(receiver)
                .alias(alias)
                .build();

        String url = "http://localhost:"+port+"/letters";

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andDo(print());

        //then
        List<Letter> all = letterRepository.findAll();
        Letter entity = all.get(0);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getContent()).isEqualTo(content);
        assertThat(entity.getSender()).isEqualTo(sender);
        assertThat(entity.getReceiver()).isEqualTo(receiver);
        assertThat(entity.getAlias()).isEqualTo(alias);
    }
}