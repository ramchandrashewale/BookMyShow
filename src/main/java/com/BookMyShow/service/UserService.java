package com.BookMyShow.service;

import com.BookMyShow.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserInfo(Long user_id);
}
