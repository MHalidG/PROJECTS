package com.hillrent.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.hillrent.domain.User;
import com.hillrent.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

	

	UserDTO userToUserDTO(User user);
	List<UserDTO> map(List<User> user);
}

