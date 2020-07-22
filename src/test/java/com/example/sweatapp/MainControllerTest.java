package com.example.sweatapp;

import com.example.sweatapp.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("grn")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/message-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/message-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MainController controller;

    @Test
    public void mainPageTest() throws Exception{

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
        .andExpect(xpath("//div[@id='navbarSupportedContent']/div").string("grn"));

    }

    @Test
    public void messageListTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(4));
    }

    @Test
    public void filterMessagesTest() throws Exception {
        this.mockMvc.perform(get("/main").param("filter","tag2"))
                .andDo(print())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(2))
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=2]").exists())
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=3]").exists())
                .andExpect(authenticated());
    }

    @Test
    public void addMessageToListTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/main")
                .file("file", "123".getBytes())
                .param("text", "fifth")
                .param("tag", "new one")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='message-list']/div").nodeCount(5))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']").exists())
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div/span").string("fifth"))
                .andExpect(xpath("//*[@id='message-list']/div[@data-id='10']/div/i").string("#new one"));
    }

}
