package com.maverickstube.maverickshub.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverickstube.maverickshub.dtos.requests.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void authenticateUserTest() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("jane@email.com");
        loginRequest.setPassword("password");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/auth")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(loginRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void authenticateUserTest_unsuccessful() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("janekk@email.com");
        loginRequest.setPassword("wrongPassword");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/auth")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

}
