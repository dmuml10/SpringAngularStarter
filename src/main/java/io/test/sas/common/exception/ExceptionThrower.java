package io.test.sas.common.exception;

@FunctionalInterface
public interface ExceptionThrower {

    void throwException() throws Throwable;

}