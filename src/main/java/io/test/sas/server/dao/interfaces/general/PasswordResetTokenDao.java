package io.test.sas.server.dao.interfaces.general;

import io.test.sas.common.entity.PasswordResetToken;

public interface PasswordResetTokenDao {

    PasswordResetToken saveToken(PasswordResetToken token);

}