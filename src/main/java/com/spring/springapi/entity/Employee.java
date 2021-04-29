package com.spring.springapi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Employee {

    @Id
    private Long id;
    private String name;
    private String company;
    private String city;
    private String salary;


    public Employee(String name, String company, String city, String salary) {
        super();
        this.name = name;
        this.company = company;
        this.city = city;
        this.salary = salary;
    }

    public Employee() {

    }
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "company", nullable = false)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    @Column(name = "city", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Column(name = "salary", nullable = false)
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", company='" + company + '\'' +
            ", city='" + city + '\'' +
            ", salary='" + salary + '\'' +
            '}';
    }
}
