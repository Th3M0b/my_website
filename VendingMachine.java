//import java.util.ArrayList;
//import java.util.List;

public class VendingMachine {
    // Fields
    private int balance = 10;  // Start with a default balance
    //private List<Items> items;
    private String location;
    public static final int MAX_CAPACITY = 10;

    // Constructors
    public VendingMachine() {
        //this.items = new ArrayList<>();
        this.location = "Unknown";
    }

    public VendingMachine(String location) {
        this();
        this.location = location;
    }

    // Getters and setters
    public double getBalance() {
        return balance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void addBalance(double amount) {
        if (amount > 0) {
            balance += amount;
            //System.out.println("New balance: $" + balance);
        } else {
            //System.out.println("Invalid amount. Cannot add negative or zero balance.");
        }
    }

    // Take back money (Overloaded method)
    public double takeBackMoney() {
        int refundedAmount = balance;
        balance = 0;
        //System.out.println("Money taken back: $" + refundedAmount);
        return refundedAmount;
    }

    // Overloaded takeBackMoney to take back a specific amount
    public boolean takeBackMoney(int amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            //System.out.println("Refunded $" + amount + ". New balance: $" + balance);
            return true;
        } else {
            //System.out.println("Invalid refund amount or insufficient balance.");
            return false;
        }
    }

    // Output method
    public void println() {
        System.out.println(this);
    }
}
