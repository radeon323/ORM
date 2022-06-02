package com.luxoft.olshevchenko.querygenerator;

import com.luxoft.olshevchenko.querygenerator.annotation.Column;
import com.luxoft.olshevchenko.querygenerator.annotation.Id;
import com.luxoft.olshevchenko.querygenerator.annotation.Table;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * @author Oleksandr Shevchenko
 */
public class QueryGeneratorUtils {

    protected static String getIdAnnotationName(Class<?> type) {
        for (Field declaredField : type.getDeclaredFields()) {
            Id idAnnotation = declaredField.getAnnotation(Id.class);
            if (idAnnotation != null) {
                return declaredField.getName();
            } else {
                throw new IllegalArgumentException("Class is not ORM entity");
            }
        }
        return null;
    }

    protected static String getTableName(Class<?> type) {
        Table tableAnnotation = type.getAnnotation(Table.class);
        if (tableAnnotation == null) {
            throw new IllegalArgumentException("Class is not ORM entity");
        }
        return tableAnnotation.name().isEmpty() ? type.getSimpleName() : tableAnnotation.name();
    }

    protected static String getColumnNames(Class<?> type) {
        StringJoiner columnNames = new StringJoiner(", ");

        for (Field declaredField : type.getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ? declaredField.getName() : columnAnnotation.name();
                columnNames.add(columnName);
            } else {
                throw new IllegalArgumentException("Class is not ORM entity");
            }
        }
        return columnNames.toString();
    }

    @SneakyThrows
    protected static String getValues(Object value) {
        StringJoiner objectValues = new StringJoiner(", ", "(", ")");

        for (Field declaredField : value.getClass().getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                declaredField.setAccessible(true);
                Object result = declaredField.get(value);
                objectValues.add(result.toString());
            } else {
                throw new IllegalArgumentException("Class is not ORM entity");
            }
        }
        return objectValues.toString();
    }

    @SneakyThrows
    protected static String getColumnNamesAndValues(Object value, boolean isId) {
        StringJoiner objectValues = new StringJoiner(", ");
        for (Field declaredField : value.getClass().getDeclaredFields()) {
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ? declaredField.getName() : columnAnnotation.name();
                StringBuilder columnNameAndValue = new StringBuilder(columnName);
                declaredField.setAccessible(true);
                columnNameAndValue.append(" = ");
                columnNameAndValue.append(declaredField.get(value));

                if (!declaredField.equals(value.getClass().getDeclaredFields()[0]) && !isId) {
                    objectValues.add(columnNameAndValue);
                } else if (declaredField.equals(value.getClass().getDeclaredFields()[0]) && isId){
                    objectValues.add(columnNameAndValue);
                }

            } else {
                throw new IllegalArgumentException("Class is not ORM entity");
            }
        }
        return objectValues.toString();
    }


}
