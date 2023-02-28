package com.gachigang.ontherun.payload.user.request;

import com.gachigang.ontherun.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBusinessRequest {
    private String name;
    private String country;
    private String city;
    private Set<User> owners;
}
