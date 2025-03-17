public class TestVendingMachine {

    public static void main(String[] args) {
        // Create a new VendingMachine instance with a specific location
        VendingMachine machine = new VendingMachine("Lobby");

        // Test initial balance (should be 10.00)
        System.out.println("Initial Balance: $" + machine.getBalance());

        // Test adding balance
        machine.addBalance(5.00);
        System.out.println("Balance after adding $" + machine.getBalance());

        // Test taking back the full balance
        System.out.println("\nTaking back all money");
        double refundedAmount = machine.takeBackMoney();
        System.out.println("Refunded amount: $" + refundedAmount);
        System.out.println("Balance after refund: $" + machine.getBalance());

        // Test adding balance again
        machine.addBalance(20.00);
        System.out.println("Balance after adding $20: $" + machine.getBalance());

        // Test taking back a specific amount
        boolean success = machine.takeBackMoney(10);
        if (success) {
            System.out.println("Balance after taking back: $" + machine.getBalance());
        } else {
            System.out.println("Failed to take back money.");
        }

        // Test invalid take-back amount
        machine.takeBackMoney(50);
    }
}
