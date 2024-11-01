package com.ucs;

/**
 * Manager
 */
public class Manager extends Employee {
    private double yearlySalary;
    private String firstName;
    private String lastName;
    private int employeeNumber;
    private double moneyEarned;

    /**
     * @param firstName      the first name of the manager
     * @param lastName       the last name of the manager
     * @param employeeNumber the employee number of the manager
     * @param yearlySalary   the yearly salary of the manager
     */
    public Manager(String firstName, String lastName, int employeeNumber, double yearlySalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
        this.yearlySalary = yearlySalary;
    }

    /**
     * @param weeksWorked the number of weeks worked by the manager
     * @param isHourly    whether the manager is paid hourly
     */
    public void pay(int weeksWorked, boolean isHourly) {
        if (!isHourly) {
            double earned = getWeeklySalary() * weeksWorked;
            System.out.printf("Manager %s %s has been paid $%.2f\n", this.getFirstName(), this.getLastName(), earned);
        }
    }

    /**
     * @return the string representation of the manager
     */
    @Override
    public String toString() {
        return "Manager " + this.getFirstName() + " " + this.getLastName() + " (" + this.getEmployeeNumber()
                + ")";
    }

    /**
     * @return the first name of the manager
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of the manager
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * @return the employee number of the manager
     */
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * @return the money earned by the manager
     */
    public double getEarnings() {
        return moneyEarned;
    }

    /**
     * @return the yearly salary of the manager
     */

    public double getYearlySalary() {
        return yearlySalary;
    }

    /**
     * @return the weekly salary of the manager
     */
    public double getWeeklySalary() {
        return yearlySalary / 52;
    }
}