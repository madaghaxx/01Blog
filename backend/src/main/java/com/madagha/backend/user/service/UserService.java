package com.madagha.backend.user.service;

import com.madagha.backend.user.entity.User;
import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(UUID id);

    User createUser(User user);

    User updateUser(UUID id, User user);

    void deleteUser(UUID id);

    void purgeUsers();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void giveAdminRights(UUID id);

    public void removeAdminRights(UUID id);

}