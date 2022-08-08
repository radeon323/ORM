package com.luxoft.olshevchenko.querygenerator;

import com.luxoft.olshevchenko.querygenerator.annotation.Column;
import com.luxoft.olshevchenko.querygenerator.annotation.Id;
import com.luxoft.olshevchenko.querygenerator.annotation.Table;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.StringJoiner;

/**
 * @author Oleksandr Shevchenko
 */
public class QueryGeneratorUtils {

    static String getTableName(Class<?> type) {
        Table tableAnnotation = type.getAnnotation(Table.class);
        String tableName;
        if (tableAnnotation != null) {
            tableName = tableAnnotation.name().isEmpty() ? type.getSimpleName() : tableAnnotation.name();
        } else {
            tableName = type.getSimpleName();
        }
        return tableName;
    }

    static String getColumnNames(Class<?> type) {
        StringJoiner columnNames = new StringJoiner(", ");

        for (Field declaredField : type.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ? declaredField.getName() : columnAnnotation.name();
                columnNames.add(columnName);
            } else {
                String columnName = declaredField.getName();
                columnNames.add(columnName);
            }
        }
        return columnNames.toString();
    }

    @SneakyThrows
    static String getValues(Object value) {
        StringJoiner objectValues = new StringJoiner(", ", "(", ")");
        for (Field declaredField : value.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            Object result = createValueInQuotesIfDeclaredFieldIsString(value, declaredField);
            objectValues.add(result.toString());
        }
        return objectValues.toString();
    }

    static String getIdColumnNameAndValue(Object value) {
        StringJoiner objectValues = new StringJoiner(", ");
        for (Field declaredField : value.getClass().getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            Id idAnnotation = declaredField.getAnnotation(Id.class);
            if (idAnnotation != null || declaredField.getName().equals("id")) {
                buildStringForQuery(value, objectValues, declaredField, columnAnnotation);
            }
        }
        return objectValues.toString();
    }

    static String getColumnNamesAndValuesExceptId(Object value) {
        StringJoiner objectValues = new StringJoiner(", ");
        for (Field declaredField : value.getClass().getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            Id idAnnotation = declaredField.getAnnotation(Id.class);
            if (idAnnotation == null && !declaredField.getName().equals("id")) {
                buildStringForQuery(value, objectValues, declaredField, columnAnnotation);
            }
        }
        return objectValues.toString();
    }

    @SneakyThrows
    private static void buildStringForQuery(Object value, StringJoiner objectValues, Field declaredField, Column columnAnnotation) {
        String columnName;
        if (columnAnnotation != null) {
            columnName = columnAnnotation.name().isEmpty() ? declaredField.getName() : columnAnnotation.name();
        } else {
            columnName = declaredField.getName();
        }
        StringBuilder columnNameAndValue = new StringBuilder(columnName);
        declaredField.setAccessible(true);
        Object result = createValueInQuotesIfDeclaredFieldIsString(value, declaredField);
        columnNameAndValue.append(" = ");
        columnNameAndValue.append(result);
        objectValues.add(columnNameAndValue);
    }

    private static Object createValueInQuotesIfDeclaredFieldIsString(Object value, Field declaredField) throws IllegalAccessException {
        Object result = declaredField.get(value);
        if (result.getClass().equals(String.class)) {
            result = "'" + result + "'";
        }
        return result;
    }


}
