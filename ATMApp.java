import java.util.Scanner;

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && account.getBalance() >= amount) {
            account.debit(amount);
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            account.credit(amount);
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Method to check balance
    public void checkBalance() {
        System.out.println("Current balance: " + account.getBalance());
    }

    // Method to display menu
    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    // Method to handle user input
    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }
}
 
// Bank Account class 
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

public class ATMApp {
    public static void main(String[] args) {
        // Create a new bank account with an initial balance
        BankAccount account = new BankAccount(1000.0);

        // Create a new ATM and associate it with the bank account
        ATM atm = new ATM(account);

        // Handle user input
        atm.handleUserInput();
    }
}
