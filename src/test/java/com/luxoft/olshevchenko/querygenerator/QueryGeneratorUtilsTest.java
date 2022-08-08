package com.luxoft.olshevchenko.querygenerator;

import com.luxoft.olshevchenko.querygenerator.entity.Person;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Shevchenko
 */
class QueryGeneratorUtilsTest {
    private final Class<?> clazz = Person.class;
    private final Class<?> clazzWithoutAnnotation = PersonWithoutAnnotation.class;
    private final Object person = new Person(1,"Bob",100.1);
    private final Object personWithoutAnnotation = new PersonWithoutAnnotation(1,"Bob",100.1);


    @Test
    @DisplayName("Test Get table name method")
    void testGetTableName() {
        String expectedString = "Person";
        String actualString = QueryGeneratorUtils.getTableName(clazz);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get table name method if annotation @Table is missing")
    void testGetTableNameIfAnnotationMissing() {
        String expectedString = "PersonWithoutAnnotation";
        String actualString = QueryGeneratorUtils.getTableName(clazzWithoutAnnotation);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get column names method")
    void testGetColumnNames() {
        String expectedString = "id, person_name, person_salary";
        String actualString = QueryGeneratorUtils.getColumnNames(clazz);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get column names method if annotation @Column is missing")
    void testGetColumnNamesIfAnnotationMissing() {
        String expectedString = "id, name, salary";
        String actualString = QueryGeneratorUtils.getColumnNames(clazzWithoutAnnotation);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get values method")
    void testGetValues() {
        String expectedString = "(1, 'Bob', 100.1)";
        String actualString = QueryGeneratorUtils.getValues(person);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get values method if annotation @Column is missing")
    void testGetValuesIfAnnotationMissing() {
        String expectedString = "(1, 'Bob', 100.1)";
        String actualString = QueryGeneratorUtils.getValues(personWithoutAnnotation);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get column names and values except id")
    void testGetColumnNamesAndValuesIfNotId() {
        String expectedString = "person_name = 'Bob', person_salary = 100.1";
        String actualString = QueryGeneratorUtils.getColumnNamesAndValuesExceptId(person);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get column names and values except id if annotation @Id is missing")
    void testGetColumnNamesAndValuesNotIdIfAnnotationMissing() {
        String expectedString = "name = 'Bob', salary = 100.1";
        String actualString = QueryGeneratorUtils.getColumnNamesAndValuesExceptId(personWithoutAnnotation);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get id column name and value")
    void testGetColumnNamesAndValuesIfId() {
        String expectedString = "id = 1";
        String actualString = QueryGeneratorUtils.getIdColumnNameAndValue(person);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get id column name and value if annotation @Id is missing")
    void testGetColumnNamesAndValuesIfIdIfAnnotationMissing() {
        String expectedString = "id = 1";
        String actualString = QueryGeneratorUtils.getIdColumnNameAndValue(personWithoutAnnotation);
        assertEquals(expectedString, actualString);
    }


    @Getter
    @Setter
    private static class PersonWithoutAnnotation {
        private int id;
        private String name;
        private double salary;

        public PersonWithoutAnnotation(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }
    }


}