package io.test.sas.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {}

    public static boolean isBlank(String... strings) {
        if (Objects.isNull(strings) || strings.length == 0) {
            return true;
        }

        return Arrays.stream(strings).anyMatch(string -> (Objects.isNull(string) || string.trim().isEmpty()));
    }

    public static <T> boolean isBlank(Collection<T> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <K, V> boolean isBlank(Map<K, V> map) {
        return Objects.isNull(map) || map.isEmpty();
    }

    public static String expandStringPattern(String pattern, int times) {
        if (Objects.isNull(pattern)) {
            throw new IllegalArgumentException("pattern cannot be null");
        }

        if (times < 0) {
            throw new IllegalArgumentException("times cannot be negative");
        }

        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= times; i++) {
            result.append(pattern);
        }

        return result.toString();
    }

    public static String getStackTrace(Throwable throwable) {
        if (Objects.isNull(throwable)) {
            throw new IllegalArgumentException("throwable cannot be null");
        }

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter, true);

        throwable.printStackTrace(printWriter);
        printWriter.flush();

        return stringWriter.toString();
    }

    public static Object[] toStringArray(Object... array) {
        return Arrays.stream(array).map(Object::toString).collect(Collectors.toList()).toArray();
    }

}