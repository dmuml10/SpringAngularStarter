package io.test.sas.common.service.general;

import io.test.sas.common.entity.AbstractAuditingEntity;
import io.test.sas.common.exception.AppException;
import io.test.sas.common.filter.general.AbstractFilter;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T extends AbstractAuditingEntity> {

    T add(T entity) throws AppException;

    T update(T entity) throws AppException;

    void delete(long id) throws AppException;

    Optional<T> get(long id);

    long getCount(AbstractFilter filter);

    List<T> getList(AbstractFilter filter);
}