package org.example;

public class EmployeePayroleData {

    public int id;
    public String name;
    public double salary;

    public EmployeePayroleData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeePayroleData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

}
