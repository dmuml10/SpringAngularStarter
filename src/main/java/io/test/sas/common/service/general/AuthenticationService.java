package io.test.sas.common.service.general;

import io.test.sas.common.exception.AppException;

public interface AuthenticationService {

    void resetPassword(String usernameOrEmail, String url) throws AppException;

}