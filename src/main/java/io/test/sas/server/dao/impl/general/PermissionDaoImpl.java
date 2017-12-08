package io.test.sas.server.dao.impl.general;

import io.test.sas.common.filter.general.AbstractFilter;
import io.test.sas.server.dao.interfaces.general.PermissionDao;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Map;

@Repository
public class PermissionDaoImpl implements PermissionDao {

    public boolean exists(long userGroupId, String permissionGroup, String permissionType) {
        return true;
    }

}