package com.colak.springtutorial.mapper;

import com.colak.springtutorial.dto.UserDto;
import com.colak.springtutorial.dto.UserDetailsDto;
import com.colak.springtutorial.jpa.User;

public class UserMapper {

    public static User mapToUser(User user, UserDto userDto) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDetailsDto mapToUserDetails(User user) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setFirstName(user.getFirstName());
        userDetailsDto.setLastName(user.getLastName());
        userDetailsDto.setEmail(user.getEmail());
        return userDetailsDto;
    }
}
