package io.test.sas.server.service.general;

import io.test.sas.common.entity.User;
import io.test.sas.common.exception.AppException;
import io.test.sas.common.filter.general.AbstractFilter;
import io.test.sas.common.service.general.UserService;
import io.test.sas.common.utils.Utils;
import io.test.sas.server.dao.interfaces.general.PermissionDao;
import io.test.sas.server.dao.interfaces.general.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao dao;

    private PermissionDao permissionDao;

    @Autowired
    public UserServiceImpl(UserDao dao, PermissionDao permissionDao) {
        this.dao = dao;
        this.permissionDao = permissionDao;
    }

    @Override
    public User add(User entity) throws AppException {
        String username = entity.getUsername();
        if (dao.exists(username)) {
            throw new AppException("USERNAME_EXISTS", username);
        }

        String password = Utils.generateRandomPassword();
        entity.setPassword(new BCryptPasswordEncoder().encode(password));

        User user = dao.update(entity);

        return user;
    }

    public User update(User entity) throws AppException {
        return dao.update(entity);
    }

    public void delete(long id) throws AppException {

    }

    public Optional<User> get(long id) {
        Optional<User> result = dao.getForAuthByUsername("dmuml10");
        return result;
    }

    @Override
    public long getCount(AbstractFilter filter) {
        return 0;
    }

    @Override
    public List<User> getList(AbstractFilter filter) {
        return null;
    }

    public boolean hasPermission(String permissionGroup, String permissionType) {
        return true;
    }
}