package io.test.sas.server.service.general;

import io.test.sas.common.entity.User;
import io.test.sas.common.enums.StatusType;
import io.test.sas.server.dao.interfaces.general.UserDao;
import io.test.sas.web.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceJwtImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserDetailsServiceJwtImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userWrapper = userDao.getForAuthByUsername(username);

        if (!userWrapper.isPresent()) {
            throw new UsernameNotFoundException("username [" + username + "] not found");
        } else {
            User user = userWrapper.get();
            JwtUser jwtUser = new JwtUser();

            jwtUser.setUsername(username);
            jwtUser.setPassword(user.getPassword());
            jwtUser.setEnabled(user.getStatus() == StatusType.ACTIVE);

            List<SimpleGrantedAuthority> permissions = new ArrayList<>();
            jwtUser.setAuthorities(permissions);
            return jwtUser;
        }
    }
}