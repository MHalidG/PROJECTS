package com.hillrent.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hillrent.domain.User;
import com.hillrent.dto.UserDTO;
import com.hillrent.dto.request.AdminUserUpdateRequest;
import com.hillrent.dto.request.UserUpdateRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

	
	UserDTO userToUserDTO(User user);
	List<UserDTO> map(List<User> user);

	@Mapping(target="id",ignore=true)
	@Mapping(target="roles", ignore=true)
	User adminUserUpdateRequestToUser(AdminUserUpdateRequest request);

	
}

