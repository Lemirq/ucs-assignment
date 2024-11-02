package com.ucs;

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