package model;

import java.security.InvalidParameterException;
import java.util.UUID;

public class Employee {
    private static int counter = 0;
    public final String ID;
    private String name;
    private double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null)
            this.name = name;
        else
            this.name = "No name";
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary > 0)
            this.salary = salary;
        else
            this.salary = 1000;
    }

    public Employee(String name, double salary) {
        this.ID = String.format("E-%03d",++counter);
        setName(name);
        setSalary(salary);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Salary: %.2f", ID, name, salary);
    }
}
