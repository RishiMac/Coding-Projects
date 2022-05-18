import java.util.Scanner;

public class BankAccount {
    int balance;
    int previousTransaction;
    String customerName;
    String customerId;

    public BankAccount(String customerName, String customerId) {
        this.customerName = customerName;
        this.customerId = customerId;
    }
    public void deposit(int amount) {
        if (amount != 0) {
            balance = balance + amount;
            previousTransaction = amount;
        }
    }
    public void withdraw(int amount) {
        if (amount != 0) {
            balance = balance - amount;
            previousTransaction = -amount;
        }
    }
    public void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println("Deposited: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrawn: " + Math.abs(previousTransaction));
        } else {
            System.out.println("No transaction occured.");
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        char option;
        System.out.println("Welecome " + customerName);
        System.out.println("Your ID is " + customerId);
        System.out.println("\n");
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Exit");

        do {
            System.out.println("********************************************************");
            System.out.println("Enter an option.");
            System.out.println("********************************************************");
            option = scanner.next().charAt(0);
            System.out.println("\n");

            switch(option) {
                case 'A':
                    System.out.println("----------------------------");
                    System.out.println("Balance: " + balance);
                    System.out.println("----------------------------");
                    System.out.println("\n");
                    break;
                case 'B':
                    System.out.println("----------------------------");
                    System.out.println("Enter an amount to deposit.");
                    System.out.println("----------------------------");
                    int depositAmount = scanner.nextInt();
                    deposit(depositAmount);
                    System.out.println("\n");
                    break;
                case 'C':
                    System.out.println("----------------------------");
                    System.out.println("Enter an amount to withdraw.");
                    System.out.println("----------------------------");
                    int withdrawnAmount = scanner.nextInt();
                    withdraw(withdrawnAmount);
                    System.out.println("\n");
                    break;
                case 'D':
                    System.out.println("----------------------------");
                    getPreviousTransaction();
                    System.out.println("----------------------------");
                    System.out.println("\n");
                    break;
                case 'E':
                    System.out.println("----------------------------");
                    break;
                default:
                    System.out.println("Invalid Option! Please try again.");
                    break;
            }
        } while(option != 'E');
        System.out.println("Thank you for banking with us! Please visit us again!");
    }
}