package com.luxoft.olshevchenko.querygenerator;

import java.io.Serializable;

import static com.luxoft.olshevchenko.querygenerator.QueryGeneratorUtils.*;

/**
 * @author Oleksandr Shevchenko
 */
public class QueryGeneratorImpl implements QueryGenerator {

    //    "SELECT id, person_name, person_salary FROM Person;"
    @Override
    public String findAll(Class<?> type) {
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(getColumnNames(type));
        query.append(" FROM ");
        query.append(getTableName(type));
        query.append(";");
        return query.toString();
    }

    //    "SELECT id, person_name, person_salary FROM Person WHERE id = 1;"
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

    //    "DELETE FROM Person WHERE id = 1;"
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

    //    "INSERT INTO Person (id, person_name, person_salary) VALUES (1, Bob, 100.1);"
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

    //    "UPDATE Person SET person_name = Bob, person_salary = 100.1 WHERE id = 1;"
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
