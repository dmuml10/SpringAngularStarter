package io.test.sas.server.service.general;

import io.test.sas.common.entity.PasswordResetToken;
import io.test.sas.common.entity.User;
import io.test.sas.common.exception.AppException;
import io.test.sas.common.service.general.AuthenticationService;
import io.test.sas.server.dao.interfaces.general.PasswordResetTokenDao;
import io.test.sas.server.dao.interfaces.general.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Async
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserDao userDao;

    private PasswordResetTokenDao passwordResetTokenDao;

    @Autowired
    public AuthenticationServiceImpl(UserDao userDao, PasswordResetTokenDao passwordResetTokenDao) {
        this.userDao = userDao;
        this.passwordResetTokenDao = passwordResetTokenDao;
    }

    @Override
    public void resetPassword(String usernameOrEmail, String url) throws AppException {
        Optional<User> userWrapper = userDao.getForPasswordResetByEmailOrUsername(usernameOrEmail);
        if (!userWrapper.isPresent()) {
            throw new AppException("USERNAME_NOT_FOUND");
        }

        String token = UUID.randomUUID().toString();
        PasswordResetToken myToken = new PasswordResetToken(token, userWrapper.get());
        passwordResetTokenDao.saveToken(myToken);

    }
}