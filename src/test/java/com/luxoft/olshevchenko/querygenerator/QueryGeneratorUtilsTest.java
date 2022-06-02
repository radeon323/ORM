package com.luxoft.olshevchenko.querygenerator;

import com.luxoft.olshevchenko.querygenerator.entity.Person;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryGeneratorUtilsTest {
    Class<?> clazz = Person.class;
    Class<?> clazzNotOrm = PersonNotOrm.class;
    Object person = setPerson();
    Object personNotOrm = setPersonNotOrm();

    private Person setPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Bob");
        person.setSalary(100.1);
        return person;
    }

    private PersonNotOrm setPersonNotOrm() {
        PersonNotOrm person = new PersonNotOrm();
        person.setId(1);
        person.setName("Bob");
        person.setSalary(100.1);
        return person;
    }

    @Test
    @DisplayName("Test Get id annotation name method")
    void testGetIdAnnotationName() {
        String expectedString = "id";
        String actualString = QueryGeneratorUtils.getIdAnnotationName(clazz);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get id annotation name method if class is not ORM entity")
    void testGetIdAnnotationNameIfNotOrm() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> QueryGeneratorUtils.getIdAnnotationName(clazzNotOrm));
    }

    @Test
    @DisplayName("Test Get table name method")
    void testGetTableName() {
        String expectedString = "Person";
        String actualString = QueryGeneratorUtils.getTableName(clazz);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get table name method if class is not ORM entity")
    void testGetTableNameIfNotOrm() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> QueryGeneratorUtils.getTableName(clazzNotOrm));
    }

    @Test
    @DisplayName("Test Get column names method")
    void testGetColumnNames() {
        String expectedString = "id, person_name, person_salary";
        String actualString = QueryGeneratorUtils.getColumnNames(clazz);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get column names method if class is not ORM entity")
    void testGetColumnNamesIfNotOrm() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> QueryGeneratorUtils.getColumnNames(clazzNotOrm));
    }

    @Test
    @DisplayName("Test Get values method")
    void testGetValues() {
        String expectedString = "(1, Bob, 100.1)";
        String actualString = QueryGeneratorUtils.getValues(person);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get values method if class is not ORM entity")
    void testGetValuesIfNotOrm() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> QueryGeneratorUtils.getValues(personNotOrm));
    }

    @Test
    @DisplayName("Test Get column names and values except id")
    void testGetColumnNamesAndValuesIfNotId() {
        String expectedString = "person_name = Bob, person_salary = 100.1";
        String actualString = QueryGeneratorUtils.getColumnNamesAndValues(person, false);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get id column name and value")
    void testGetColumnNamesAndValuesIfId() {
        String expectedString = "id = 1";
        String actualString = QueryGeneratorUtils.getColumnNamesAndValues(person, true);
        assertEquals(expectedString, actualString);
    }

    @Test
    @DisplayName("Test Get column names and values except id if class is not ORM entity")
    void testGetColumnNamesAndValuesIfNotOrm() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> QueryGeneratorUtils.getColumnNamesAndValues(personNotOrm, false));
    }

    @Getter
    @Setter
    private static class PersonNotOrm {
        private int id;
        private String name;
        private double salary;
    }
}