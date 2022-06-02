package com.luxoft.olshevchenko.querygenerator;

import java.io.Serializable;

import static com.luxoft.olshevchenko.querygenerator.QueryGeneratorUtils.*;

/**
 * @author Oleksandr Shevchenko
 */
public class QueryGeneratorImpl implements QueryGenerator {

    @Override
    public String findAll(Class<?> type) {
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(getColumnNames(type));
        query.append(" FROM ");
        query.append(getTableName(type));
        query.append(";");
        return query.toString();
    }

    @Override
    public String findById(Class<?> type, Serializable id) {
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(getColumnNames(type));
        query.append(" FROM ");
        query.append(getTableName(type));
        query.append(" WHERE ");
        query.append(getIdAnnotationName(type));
        query.append(" = ");
        query.append(id);
        query.append(";");
        return query.toString();
    }

    @Override
    public String deleteById(Class<?> type, Serializable id) {
        StringBuilder query = new StringBuilder("DELETE FROM ");
        query.append(getTableName(type));
        query.append(" WHERE ");
        query.append(getIdAnnotationName(type));
        query.append(" = ");
        query.append(id);
        query.append(";");
        return query.toString();
    }

    @Override
    public String insert(Object value) {
        Class<?> type = value.getClass();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(getTableName(type));
        query.append(" (");
        query.append(getColumnNames(type));
        query.append(") ");
        query.append("VALUES ");
        query.append(getValues(value));
        query.append(";");
        return query.toString();
    }

    @Override
    public String update(Object value) {
        Class<?> type = value.getClass();
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(getTableName(type));
        query.append(" SET ");
        query.append(getColumnNamesAndValues(value, false));
        query.append(" WHERE ");
        query.append(getColumnNamesAndValues(value, true));
        query.append(";");
        return query.toString();
    }


}
