package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.model.dto.UserDto;
import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.payload.user.response.UserRegisterResponse;
import com.gachigang.ontherun.persistence.entity.User;
import org.mapstruct.Mapper;
/**
 * This interface defines methods for mapping between the {@link User} and {@link UserDto} classes.
 */
@Mapper
public interface UserMapper {
    /**
     * Maps a {@link UserDto} object to a {@link User} object.
     *
     * @param entity The {@link User} object to be mapped.
     * @return The resulting {@link UserRegisterResponse} object.
     */

    UserRegisterResponse toDto(User entity);


    /** Maps a {@link UserDto } object to a {@link  User} object.
     *
     * @param dto The {@link LoginRequest} object to be mapped.
     * @return The resulting {@link User} object.
     */
    User fromDto(LoginRequest dto);
    User fromDto(RegisterRequest dto);
}
