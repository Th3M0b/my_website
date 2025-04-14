package snackandcoffee;
import java.util.HashMap;
import java.util.Map;

import vendingmachine.VendingMachine;

public class SnackVendingMachine extends VendingMachine{
    private Map<String, Integer> snacks = new HashMap<>();

    public SnackVendingMachine(String location) {
        super(location);
    }

    public void addSnack(String snack, int quantity) {
        snacks.put(snack, snacks.getOrDefault(snack, 0) + quantity);
    }

    public boolean buySnack(String snack){
        if (snacks.getOrDefault(snack, 0) > 0){
            snacks.put(snack, snacks.get(snack) -1);
            return true;
        }
        return false;
    }

    @Override
    public boolean takeBackMoney(int amount) {
    // Check if there have been any snack purchases (or another condition).
        if (snacks.isEmpty()) {
            return false;
        }
    // Proceed with refund if the conditions are met.
        return super.takeBackMoney(amount);
    }


    @Override
    public String toString(){
        return "SnackVendingMachine at " + getLocation() + " | Snacks: " + snacks;
    }
}
