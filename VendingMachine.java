import java.util.ArrayList;
import java.util.List;

public class VendingMachine {
    // Fields
    private double balance = 10.00;  // Start with a default balance
    private List<Drink> drinks;
    private String location;
    public static final int MAX_CAPACITY = 10;

    // Constructors
    public VendingMachine() {
        this.drinks = new ArrayList<>();
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

    // Drink management (overloaded methods)
    public void addDrink(String name, double price) {
        addDrink(name, price, 1);
    }

    public void addDrink(String name, double price, int quantity) {
        for (Drink drink : drinks) {
            if (drink.getName().equalsIgnoreCase(name) && drink.getPrice() == price) {
                drink.setQuantity(drink.getQuantity() + quantity);
                return;
            }
        }
        
        if (drinks.size() < MAX_CAPACITY) {
            drinks.add(new Drink(name, price, quantity));
        } else {
            System.out.println("Max capacity reached. Cannot add new drink type.");
        }
    }

    // Purchase
    public boolean selectDrink(int index) {
        if (index >= 0 && index < drinks.size()) {
            Drink selected = drinks.get(index);
            if (selected.getQuantity() == 0) {
                System.out.println("Sorry, " + selected.getName() + " is out of stock.");
                return false;
            }
            if (balance < selected.getPrice()) {
                System.out.println("Not enough balance to buy " + selected.getName() + " ($" + selected.getPrice() + ")");
                return false;
            }

            // Successful purchase
            balance -= selected.getPrice();
            selected.setQuantity(selected.getQuantity() - 1);
            System.out.println("Purchased " + selected.getName() + " for $" + selected.getPrice());
            return true;
        }
        System.out.println("Invalid selection.");
        return false;
    }

    // Output method
    public void println() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Vending Machine Status ===\n");
        sb.append("Location: ").append(location).append("\n");
        sb.append(String.format("Balance: $%.2f%n", balance));
        sb.append("Available Drinks:\n");
        
        for (int i = 0; i < drinks.size(); i++) {
            Drink drink = drinks.get(i);
            sb.append(i + 1).append(". ")
              .append(drink.getName()).append(" - $")
              .append(String.format("%.2f", drink.getPrice()))
              .append(" (").append(drink.getQuantity()).append(" left)\n");
        }
        return sb.toString();
    }
}
