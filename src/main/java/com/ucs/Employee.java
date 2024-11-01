package com.ucs;

/**
 * Employee
 */
// We can model a UCS employee with an object. Since all employees will be
// either managers or associates, this will be an employee template.
// UCS Employees have a first name, a last name, and employee number.
// A toString() method will return a String with the employee’s name and
// employee number.
// You can pay() an employee. Managers and associates are paid differently.

/**
 * Employee
 */
abstract class Employee {
    abstract void pay(int amount, boolean isHourly);

    @Override
    public abstract String toString();

    abstract public String getFirstName();

    abstract public String getLastName();

    abstract public int getEmployeeNumber();

    abstract public double getEarnings();
}