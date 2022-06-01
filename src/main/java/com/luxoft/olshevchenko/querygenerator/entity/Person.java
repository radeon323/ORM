package com.luxoft.olshevchenko.querygenerator.entity;

import com.luxoft.olshevchenko.querygenerator.annotation.Column;
import com.luxoft.olshevchenko.querygenerator.annotation.Id;
import com.luxoft.olshevchenko.querygenerator.annotation.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Oleksandr Shevchenko
 */
@Getter
@Setter
@Table()
public class Person {

    @Id
    @Column()
    private int id;

    @Column(name = "person_name")
    private String name;

    @Column(name = "person_salary")
    private double salary;
}
