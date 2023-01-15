package io.getarray.userservice.service;



import io.getarray.userservice.domain.Role;
import io.getarray.userservice.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole (Role role);
    void addRoleToUsername(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
