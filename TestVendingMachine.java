class TestVendingMachine {
    public static void main(String[] args) {
        // Create vending machines
        VendingMachine campusVM = new VendingMachine();
        campusVM.setLocation("Campus Cafeteria");
        
        VendingMachine libraryVM = new VendingMachine("Library Lounge");

        // Add drinks to campus machine
        campusVM.addDrink("Espresso", 1.80);
        campusVM.addDrink("Cappuccino", 2.20, 5);
        campusVM.addDrink("Hot Chocolate", 1.50, 3);

        // Add more drinks using overloaded method
        campusVM.addDrink("Espresso", 1.80);  // Restock

        // Test output
        System.out.println("\nCampus Vending Machine:");
        campusVM.println();

        // Test library machine
        libraryVM.addDrink("Green Tea", 1.00, 2);
        libraryVM.selectDrink(0);

        System.out.println("\nLibrary Vending Machine:");
        libraryVM.println();

        campusVM.selectDrink(0);  // Buy Espresso
        libraryVM.selectDrink(0);
    }
}
