package com.luxoft.olshevchenko.querygenerator;

import java.io.Serializable;

/**
 * @author Oleksandr Shevchenko
 */
public interface QueryGenerator {
    String findAll(Class<?> type);

    String findById(Class<?> type, Serializable id);

    String deleteById(Class<?> type, Serializable id);

    String insert(Object value);

    String update(Object value);
}
