package fiiPractic.TwitterWebApp.repos.util.converter;

import fiiPractic.TwitterWebApp.model.entity.UserEntity;
import fiiPractic.TwitterWebApp.model.dto.UserDto;

public class UserConverter {
    public static UserDto toUserDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}
