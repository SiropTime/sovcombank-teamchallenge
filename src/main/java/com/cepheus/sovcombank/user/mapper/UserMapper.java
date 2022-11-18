package com.cepheus.sovcombank.user.mapper;

import com.cepheus.sovcombank.user.dto.UserInputDto;
import com.cepheus.sovcombank.user.model.User;

public class UserMapper {
    public static User mapUserInputDtoToUser(UserInputDto userInputDto){
        return User.builder()
                .name(userInputDto.getName())
                .email(userInputDto.getEmail())
                .password(userInputDto.getPassword())
                .build();
    }
}
