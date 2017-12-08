package io.test.sas.server.dao.impl.general;

import io.test.sas.common.entity.User;
import io.test.sas.common.exception.AppException;
import io.test.sas.common.filter.general.AbstractFilter;
import io.test.sas.common.filter.general.UserFilter;
import io.test.sas.common.utils.Utils;
import io.test.sas.server.dao.interfaces.general.UserDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    protected EntityManager em;

    public User update(User entity) {
        TypedQuery<String> query = em.createQuery("SELECT password FROM User WHERE id = :id", String.class);
        query.setParameter("id", entity.getId());

        entity.setPassword(query.getSingleResult());

        return em.merge(entity);
    }

    protected String getSelect() {
        return "id, username, email, name, status, language, userGroup.name";
    }

    protected void addFilter(StringBuilder queryBuilder, Map<String, Object> params, AbstractFilter abstractFilter) {
        UserFilter filter = (UserFilter) abstractFilter;

        Long id = filter.getId();
        if (Objects.nonNull(id)) {
            queryBuilder.append(" AND id = :id");
            params.put("id", id);
        }

        String username = filter.getUsername();
        if (!Utils.isBlank(username)) {
            queryBuilder.append(" AND UPPER(username) LIKE :username");
            params.put("username", "%" + username.toUpperCase() + "%");
        }

        String email = filter.getEmail();
        if (!Utils.isBlank(email)) {
            queryBuilder.append(" AND UPPER(email) LIKE :email");
            params.put("email", "%" + email.toUpperCase() + "%");
        }

        List<String> names = filter.getNames();
        if (!Utils.isBlank(names)) {
            queryBuilder.append(" AND name IN(:names)");
            params.put("names", names);
        }

        Long userGroupId = filter.getUserGroupId();
        if (!Objects.isNull(userGroupId)) {
            queryBuilder.append(" AND userGroup.id = :userGroupId");
            params.put("userGroupId", userGroupId);
        }
    }

    protected String getOrderBy() {
        return "username";
    }

//    misc

    @Override
    public Optional<User> getForAuthByUsername(String username) {
        TypedQuery<User> query = em.createQuery("FROM User WHERE UPPER(username) = :username", User.class);
        query.setParameter("username", username.toUpperCase());
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getForPermissionCheckByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT new User(userGroup) FROM User WHERE UPPER(username) = :username", User.class);
        query.setParameter("username", username.toUpperCase());

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Optional<User> getForPasswordResetByEmailOrUsername(String usernameOrEmail) {
        TypedQuery<User> query = em.createQuery("SELECT new User(id) FROM User WHERE UPPER(email) = :email OR UPPER(username) = :username", User.class);

        usernameOrEmail = usernameOrEmail.toUpperCase();
        query.setParameter("email", usernameOrEmail);
        query.setParameter("username", usernameOrEmail);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean exists(String username) {
        TypedQuery<User> query = em.createQuery("SELECT new User(id) FROM User WHERE UPPER(username) = :username", User.class);
        query.setParameter("username", username.toUpperCase());

        return !Utils.isBlank(query.getResultList());
    }

    @Override
    public boolean exists(long userGroupId) {
        TypedQuery<User> query = em.createQuery("SELECT new User(id) FROM User WHERE userGroup.id = :userGroupId", User.class);
        query.setParameter("userGroupId", userGroupId);

        return !Utils.isBlank(query.getResultList());
    }
}