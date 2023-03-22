package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.model.dto.UserDto;
import com.gachigang.ontherun.persistence.entity.User;
import org.mapstruct.Mapper;

/**
 * This interface defines methods for mapping between the {@link User} and {@link UserDto} classes.
 */
@Mapper
public interface UserMapper {

    /**
     * Maps a {@link User} object to a {@link UserDto} object.
     *
     * @param user The {@link User} object to be mapped.
     * @return The resulting {@link UserDto} object.
     */
    UserDto fromDto(User user);

    /**
     * Maps a {@link UserDto} object to a {@link User} object.
     *
     * @param userDto The {@link UserDto} object to be mapped.
     * @return The resulting {@link User} object.
     */
    User toDto(UserDto userDto);
}
