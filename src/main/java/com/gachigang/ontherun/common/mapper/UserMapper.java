package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.payload.user.response.UserRegisterResponse;
import com.gachigang.ontherun.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserRegisterResponse toDto(User entity);
    User fromDto(LoginRequest dto);
    User fromDto(RegisterRequest dto);
}
