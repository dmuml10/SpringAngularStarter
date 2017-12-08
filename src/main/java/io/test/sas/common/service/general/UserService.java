package io.test.sas.common.service.general;

import io.test.sas.common.entity.User;

public interface UserService extends AbstractService<User> {

    boolean hasPermission(String permissionGroup, String permissionType);

}