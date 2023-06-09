package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployePayroleService {

    public enum IOService {CONSOLE_IO, FILE_IO}

    private static List<EmployeePayroleData> employeePayrollList;

    public EmployePayroleService() {
    }


    public EmployePayroleService(List<EmployeePayroleData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;

    }

    public static void main(String[] args) {
        employeePayrollList = new ArrayList<>();
        EmployePayroleService employePayroleService = new EmployePayroleService();
        employePayroleService.readEmployeePayrollData();
        employePayroleService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }

    public void readEmployeePayrollData() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Employee Id ");
        int id = input.nextInt();
        System.out.println("Enter Employee Name");
        String name = input.next();
        System.out.println("Enter Employee Salary");
        double salary = input.nextDouble();
        employeePayrollList.add(new EmployeePayroleData(id, name, salary));
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("Writting to Console \n" + employeePayrollList);
        else if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIO().writeData(employeePayrollList);
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            new EmployeePayrollFileIO().printData();
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIO().countEntries();
        return 0;
    }

    public long readEmployeePayroll(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO))
            return new EmployeePayrollFileIO().readData();
        return 0;
    }
}