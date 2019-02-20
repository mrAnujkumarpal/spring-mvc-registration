package com.ems.Service;


import com.ems.Persistence.model.User;
import com.ems.Web.dto.UserDto;

public interface UserService {

    User findUserByEmail(String email);

    void createUserAccount(UserDto user);

    void updatePassword(String password, int userId);

}
