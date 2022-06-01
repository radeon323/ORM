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
        String selectAllQuery = queryGenerator.findAll(Person.class);
        assertEquals(expectedQuery, selectAllQuery);
    }

    @Test
    @DisplayName("Test FindById method")
    void testFindById() {
        String expectedQuery = "SELECT id, person_name, person_salary FROM Person WHERE id = 1;";
        String selectAllQuery = queryGenerator.findById(Person.class, person.getId());
        assertEquals(expectedQuery, selectAllQuery);
    }

    @Test
    @DisplayName("Test DeleteById method")
    void testDeleteById() {
        String expectedQuery = "DELETE FROM Person WHERE id = 1;";
        String selectAllQuery = queryGenerator.deleteById(Person.class, person.getId());
        assertEquals(expectedQuery, selectAllQuery);
    }

    @Test
    @DisplayName("Test Insert method")
    void testInsert() {
        String expectedQuery = "INSERT INTO Person (id, person_name, person_salary) VALUES (1, Bob, 100.1);";
        String selectAllQuery = queryGenerator.insert(person);
        assertEquals(expectedQuery, selectAllQuery);
    }

    @Test
    @DisplayName("Test Update method")
    void testUpdate() {
        String expectedQuery = "UPDATE Person SET person_name = Bob, person_salary = 100.1 WHERE id = 1;";
        String selectAllQuery = queryGenerator.update(person);
        assertEquals(expectedQuery, selectAllQuery);
    }


}