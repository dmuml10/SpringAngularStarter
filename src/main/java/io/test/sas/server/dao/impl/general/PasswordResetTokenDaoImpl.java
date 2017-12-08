package io.test.sas.server.dao.impl.general;

import io.test.sas.common.entity.PasswordResetToken;
import io.test.sas.server.dao.interfaces.general.PasswordResetTokenDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PasswordResetTokenDaoImpl implements PasswordResetTokenDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PasswordResetToken saveToken(PasswordResetToken token) {
        em.persist(token);
        return token;
    }

}