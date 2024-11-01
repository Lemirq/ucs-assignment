package com.ucs;

/**
 * SalesAssociate
 */
public class SalesAssociate extends Employee {
    private double hourlyRate;
    private int employeeNumber;
    String firstName, lastName;
    private double moneyEarned;

    /**
     * 
     * @param firstName      the first name of the sales associate
     * @param lastName       the last name of the sales associate
     * @param employeeNumber the employee number of the sales associate
     * @param hourlyRate     the hourly rate of the sales associate
     */
    public SalesAssociate(String firstName, String lastName, int employeeNumber, double hourlyRate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeNumber = employeeNumber;
        this.hourlyRate = hourlyRate;
    }

    /**
     * 
     * @param hoursWorked the number of hours worked by the sales associate
     * @param isHourly    whether the sales associate is paid hourly
     */
    public void pay(int hoursWorked, boolean isHourly) {
        if (isHourly) {
            double earned = getHourlyRate() * hoursWorked;
            System.out.printf("Sales Associate %s %s has been paid $%.2f\n", this.getFirstName(), this.getLastName(),
                    earned);
            moneyEarned += earned;
        }
    }

    /**
     * 
     * @return the string representation of the sales associate
     */
    @Override
    public String toString() {
        return "Sales Associate " + this.getFirstName() + " " + this.getLastName() + " (" + this.getEmployeeNumber()
                + ")";
    }

    /**
     * @return the first name of the sales associate
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of the sales associate
     */

    public String getLastName() {
        return lastName;
    }

    /**
     * @return the employee number of the sales associate
     */
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * @return the money earned by the sales associate
     */
    public double getEarnings() {
        return moneyEarned;
    }

    /**
     * @return the hourly rate of the sales associate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }
}