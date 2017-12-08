package io.test.sas.server.dao.interfaces.general;

import io.test.sas.common.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getForAuthByUsername(String username);

    Optional<User> getForPermissionCheckByUsername(String username);

    Optional<User> getForPasswordResetByEmailOrUsername(String usernameOrEmail);

    boolean exists(String username);

    boolean exists(long userGroupId);

    User update(User entity);

}