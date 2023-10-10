package com.coder.service;


import com.coder.Entity.User;
import com.coder.Entity.UserDto;

import java.util.List;

public interface IUser {

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user,Integer id);

    UserDto SearchByIdUser(int id);

    List<UserDto> getListAll();

    void deleteUser(Integer id);

}
