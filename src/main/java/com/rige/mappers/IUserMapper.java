package com.rige.mappers;

import com.rige.dto.response.UserResponse;
import com.rige.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserResponse toResponse(UserEntity entity);
}
