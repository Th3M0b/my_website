import vendingmachine.VendingMachine;
import snackandcoffee.CoffeeVendingMachine;
import snackandcoffee.SnackVendingMachine;

public class TestVendingMachine {
    public static void main(String[] args) {
        // Create instances of both types of vending machines
        VendingMachine snackMachine = new SnackVendingMachine("Library");
        VendingMachine coffeeMachine = new CoffeeVendingMachine("Office", 10);

        // Polymorphism to interact with the vending machines
        //testVendingMachine(snackMachine);
        //testVendingMachine(coffeeMachine);
        snackMachine.setLocation("shop");
        coffeeMachine.addBalance(10);
    }

    // A generic method to test any type of vending machine
    public static void testVendingMachine(VendingMachine vendingMachine) {
        System.out.println("Testing vending machine at location: " + vendingMachine.getLocation());

        // Add balance
        vendingMachine.addBalance(10);
        System.out.println("Balance after adding: $" + vendingMachine.getBalance());

        // Try to take back money
        double refund = vendingMachine.takeBackMoney();
        System.out.println("Refunded: $" + refund);

        // Print the state of the vending machine
        System.out.println(vendingMachine);

        // Check if the vending machine is a SnackVendingMachine
        if (vendingMachine instanceof SnackVendingMachine) {
            SnackVendingMachine snackMachine = (SnackVendingMachine) vendingMachine;
            snackMachine.addSnack("Chips", 5);
            snackMachine.addSnack("Candy", 3);

            // Test buying a snack
            if (snackMachine.buySnack("Chips")) {
                System.out.println("Chips dispensed!");
            } else {
                System.out.println("Chips are out of stock.");
            }
        }

        // Check if the vending machine is a CoffeeVendingMachine
        if (vendingMachine instanceof CoffeeVendingMachine) {
            CoffeeVendingMachine coffeeMachine = (CoffeeVendingMachine) vendingMachine;
            coffeeMachine.dispenseCoffee();
            System.out.println("Coffee dispensed!");
        }

        System.out.println("----------------------------------------");
    }
}