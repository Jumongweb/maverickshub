package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.requests.CreateUserRequest;
import com.maverickstube.maverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.maverickshub.models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void registerTest(){
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("testTwo@example.com");
        request.setPassword("password");

        CreateUserResponse response = userService.register(request);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("user registered successfully"));

    }

    @Test
    @DisplayName("Test that user can be retrieved by id")
    public void testGetUserById(){
        User user = userService.getById(200L);
        assertThat(user).isNotNull();
    }


}
