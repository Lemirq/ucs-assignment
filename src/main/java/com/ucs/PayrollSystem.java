package com.ucs;

import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.util.ArrayList;
import java.util.Random;

public class PayrollSystem {
    Scanner sc = new Scanner(System.in);
    ArrayList<Employee> employees = new ArrayList<Employee>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    Clip clip;

    public static void main(String[] args) {
        PayrollSystem ps = new PayrollSystem();
        ps.setup();
        ps.run();

    }

    /**
     * Setup the initial employees
     */
    void setup() {
        Manager diego = new Manager("Diego", "Martin", makeNewEmployeeID(), 55000);
        SalesAssociate kylie = new SalesAssociate("Kylie", "Walter", makeNewEmployeeID(), 18.50);
        SalesAssociate michael = new SalesAssociate("Michael", "Rose", makeNewEmployeeID(), 16.75);

        employees.add(diego);
        employees.add(kylie);
        employees.add(michael);
        setupSound("output.wav");
    }

    /**
     * Run the payroll system
     */
    void run() {
        // run a menu
        printHeader("Welcome to the Unreal Computer Sales Payroll System");

        while (true) {
            // sleep for 5 ms to prevent
            printHeader("Main Menu");
            System.out.println("\n1. Add a new employee");
            System.out.println("2. Pay an employee");
            System.out.println("3. Pay ALL employees");
            System.out.println("4. Display employees' information");
            System.out.println("5. Fire an employee");
            System.out.println("6. Quit\n");

            // validate the number input by user
            int choice = getValidInt();

            switch (choice) {
                case 1:
                    printHeader("Add a new employee");
                    System.out.println("Enter the first name of the new employee");
                    String firstName = sc.nextLine();

                    // capitalize first letter of first name and last name
                    firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
                    System.out.println("Enter the last name of the new employee");
                    String lastName = sc.nextLine();
                    lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();

                    // while loop to get the position of the new employee
                    while (true) {
                        System.out.println("Enter the position of the new employee (1. Sales Associate, 2. Manager)");
                        int position = getValidInt();

                        if (position == 1) {
                            System.out.println(
                                    "Enter the hourly rate of the new sales associate (between $16.50 and $19.75)");
                            double hourlyRate = getValidDouble();
                            while (hourlyRate < 16.50 || hourlyRate > 19.75) {
                                printRed("Invalid hourly rate. Please enter a rate between $16.50 and $19.75");
                                hourlyRate = getValidDouble();
                            }
                            employees.add(new SalesAssociate(firstName, lastName, makeNewEmployeeID(), hourlyRate));
                            break;
                        } else if (position == 2) {
                            System.out.println("Enter the yearly salary of the new manager");
                            double salary = sc.nextDouble();
                            employees.add(new Manager(firstName, lastName, makeNewEmployeeID(), salary));
                            break;
                        } else {
                            System.out.println("Invalid position");
                        }
                    }

                    // print employee info
                    printGreen("Employee added: " + employees.get(employees.size() - 1).toString());
                    break;
                case 2:
                    printHeader("Pay an employee");
                    Employee employee = getEmployee("pay");

                    // get hours worked
                    if (employee instanceof Manager) {
                        System.out.println("How many weeks did " + employee.toString() + " work?");
                        int weeks = getValidInt();
                        employee.pay(weeks, false);

                    } else if (employee instanceof SalesAssociate) {
                        System.out.println("How many hours did " + employee.toString() + " work?");
                        int hours = getValidInt();
                        employee.pay(hours, true);
                    }

                    break;
                case 3:
                    // pay all employees
                    printHeader("Pay ALL employees");
                    for (Employee emp : employees) {
                        if (emp instanceof Manager) {
                            System.out.println();
                            System.out.println(emp.getFirstName() + " is a Manager. Enter how many weeks they worked:");
                            int weeksWorked = getValidInt();
                            emp.pay(weeksWorked, false);
                        } else if (emp instanceof SalesAssociate) {
                            System.out.println();
                            System.out.println(
                                    emp.getFirstName() + " is a Sales Associate. Enter how many hours they worked:");
                            int hoursWorked = getValidInt();
                            emp.pay(hoursWorked, true);
                        }
                    }
                    break;

                case 4:
                    printHeader("Display employees' information");
                    printEmployees(employees);

                    break;

                case 5:
                    // play getout.mp3 file, and remove an employee
                    printHeader("Fire an employee");
                    Employee emp = getEmployee("fire");
                    if (emp != null) {
                        employees.remove(emp);
                        printGreen("Employee removed");
                        printRed("GET OUTTTTT!!!!!");
                        // play sound
                        clip.start();
                        // sleep for3 seconds
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // reset to start
                        clip.stop();
                        clip.setFramePosition(0);
                    }

                    break;
                case 6:
                    printGreen("Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }

        }
    }

    /*
     * Print a separator line
     */
    void printSeparator() {
        System.out.println("===============================================");
    }

    /**
     * Print a header
     * 
     * @param header string to print
     */
    void printHeader(String header) {
        System.out.println();
        printSeparator();
        printGreen(header);
        printSeparator();
    }

    /**
     * Get a valid integer from the user
     * 
     * @return the valid integer
     */
    int getValidInt() {
        int number = 0;
        while (true) {
            String input = sc.nextLine();
            try {
                number = Integer.parseInt(input);
                if (number < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                printRed("Please enter a valid non-negative number");
            }
        }

        return number;
    }

    /**
     * Get a valid double from the user
     * 
     * @return the valid double
     */
    double getValidDouble() {
        double number = 0;
        while (true) {
            String input = sc.nextLine();
            try {
                number = Double.parseDouble(input);
                if (number < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                printRed("Please enter a valid non-negative number");
            }
        }

        return number;
    }

    /**
     * Print a table of employees
     * 
     * @param employees the list of employees to print
     */
    void printEmployees(ArrayList<Employee> employees) {
        // passed an arraylist, print a table with headers: Position, Name (first name +
        // last name), ID, Earnings(yearly, or hourly)
        System.out.printf("%-20s %-20s %-10s %-15s%n", "Position", "Name", "ID", "Earnings");
        for (Employee employee : employees) {
            String position;
            if (employee instanceof Manager) {
                position = "Manager";
            } else {
                position = "Sales Associate";
            }

            double earnings = position.equals("Manager") ? ((Manager) employee).getYearlySalary()
                    : ((SalesAssociate) employee).getHourlyRate();

            String hourlyOrWeekly = position.equals("Manager") ? "Yearly" : "Hourly";

            System.out.printf("%-20s %-20s %-10d $%-15.2f (%s)%n", position,
                    employee.getFirstName() + " " + employee.getLastName(),
                    employee.getEmployeeNumber(), earnings, hourlyOrWeekly);
        }
    }

    /**
     * Search for employees by first name
     * 
     * @return the list of matching employees
     */
    ArrayList<Employee> searchEmployees() {
        // prompt user for first name
        // print list of employees with that first name

        System.out.println("Enter the first name of the employee:");
        String firstName = sc.nextLine().trim();
        ArrayList<Employee> matchingEmployees = new ArrayList<Employee>();
        for (Employee employee : employees) {
            if (employee.getFirstName().equalsIgnoreCase(firstName)) {
                matchingEmployees.add(employee);
            }
        }

        if (matchingEmployees.size() == 0) {
            printRed("No employees with that first name found");
            return null;
        }

        printGreen("Employees with that first name:");
        printEmployees(matchingEmployees);

        return matchingEmployees;
    }

    /**
     * Get an employee by ID
     * 
     * @param verb the verb to use in the prompt
     * 
     * @return the employee with that ID
     */
    Employee getEmployee(String verb) {
        // search for employees by first name
        // prompt user for ID of employee to pay
        // return the employee with that ID

        ArrayList<Employee> matchingEmployees = searchEmployees();
        if (matchingEmployees == null) {
            return null;
        }

        System.out.println("\nEnter the ID of the employee you want to " + verb);
        int id = getValidInt();

        for (Employee employee : matchingEmployees) {
            if (employee.getEmployeeNumber() == id) {
                return employee;
            }
        }

        printRed("No employee with that ID found");
        return null;
    }

    /**
     * Print a message with color
     * 
     * @param color   the color to print
     * @param message the message to print
     */
    static void printWithColor(String color, String message) {
        System.out.println(color + message + ANSI_RESET);
    }

    /**
     * Print a message in green
     * 
     * @param message the message to print
     */
    static void printGreen(String message) {
        printWithColor(ANSI_GREEN, message);
    }

    /**
     * Print a message in red
     * 
     * @param message the message to print
     */
    static void printRed(String message) {
        printWithColor(ANSI_RED, message);
    }

    /**
     * Make a new employee ID
     * 
     * @return the new employee ID, a random 8-digit number that is unique
     */
    int makeNewEmployeeID() {
        // with random class, check if any other employee has that id, if not return it
        Random rand = new Random();
        int id = rand.nextInt(00000000, 99999999);
        for (Employee employee : employees) {
            if (employee.getEmployeeNumber() == id) {
                return makeNewEmployeeID();
            }
        }

        return id;
    }

    /**
     * Setup the sound
     * 
     * @param soundName the name of the sound file in the resources folder
     */
    public void setupSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(getClass().getResource("/" + soundName));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.out.println("Error playing sound" + e);
        }
    }

}
