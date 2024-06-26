package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dtos.requests.CreateUserRequest;
import com.maverickstube.maverickshub.dtos.response.CreateUserResponse;
import com.maverickstube.maverickshub.models.User;

public interface UserService {
    CreateUserResponse register(CreateUserRequest request);
    User getById(long id);

    User getUserByUsername(String username);
}
