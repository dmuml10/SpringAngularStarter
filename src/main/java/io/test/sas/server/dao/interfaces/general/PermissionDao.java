package io.test.sas.server.dao.interfaces.general;

public interface PermissionDao {

    boolean exists(long userGroupId, String permissionGroup, String permissionType);

}