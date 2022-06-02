package com.luxoft.olshevchenko.querygenerator;

import com.luxoft.olshevchenko.querygenerator.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryGeneratorImplTest {

    QueryGenerator queryGenerator = new QueryGeneratorImpl();
    Person person = setPerson();

    private Person setPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("Bob");
        person.setSalary(100.1);
        return person;
    }


    @Test
    @DisplayName("Test FindAll method")
    void testFindAll() {
        String expectedQuery = "SELECT id, person_name, person_salary FROM Person;";
        String actualQuery = queryGenerator.findAll(Person.class);
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("Test FindById method")
    void testFindById() {
        String expectedQuery = "SELECT id, person_name, person_salary FROM Person WHERE id = 1;";
        String actualQuery = queryGenerator.findById(Person.class, person.getId());
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("Test DeleteById method")
    void testDeleteById() {
        String expectedQuery = "DELETE FROM Person WHERE id = 1;";
        String actualQuery = queryGenerator.deleteById(Person.class, person.getId());
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("Test Insert method")
    void testInsert() {
        String expectedQuery = "INSERT INTO Person (id, person_name, person_salary) VALUES (1, Bob, 100.1);";
        String actualQuery = queryGenerator.insert(person);
        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("Test Update method")
    void testUpdate() {
        String expectedQuery = "UPDATE Person SET person_name = Bob, person_salary = 100.1 WHERE id = 1;";
        String actualQuery = queryGenerator.update(person);
        assertEquals(expectedQuery, actualQuery);
    }


}