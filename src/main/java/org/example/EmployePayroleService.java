package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployePayroleService {

    public static void main(String[] args) {
        ArrayList<EmployeePayroleData> employeePayroleList = new ArrayList<>();
        EmployePayroleService employePayroleService = new EmployePayroleService();
        Scanner input = new Scanner(System.in);
        employePayroleService.readEmployeeData(input, employeePayroleList);
        employePayroleService.writeEmployeeData(employeePayroleList);
    }

    public void readEmployeeData(Scanner input, ArrayList<EmployeePayroleData> employeePayroleList) {
        System.out.println("Enter Employee Id ");
        int id = input.nextInt();
        System.out.println("Enter Employee Name");
        String name = input.next();
        System.out.println("Enter Employee Salary");
        double salary = input.nextDouble();
        employeePayroleList.add(new EmployeePayroleData(id, name, salary));
    }

    public void writeEmployeeData(ArrayList<EmployeePayroleData> employeePayroleList) {
        System.out.println("Data is = " + employeePayroleList);
    }

}