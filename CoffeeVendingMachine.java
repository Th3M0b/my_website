package snackandcoffee;
import vendingmachine.VendingMachine;

public class CoffeeVendingMachine extends VendingMachine {
    private int coffeeCups;
    private boolean freeCoffee= false;

    public CoffeeVendingMachine(String location, int initialCups){
        super(location);
        this.coffeeCups = initialCups;
    }

    public void refillCups(int amount){
        coffeeCups += amount;
    }

    public boolean dispenseCoffee(){
        if(coffeeCups > 0){
            coffeeCups--;
            return true;
        }
        return false;
    }

    @Override
    public void addBalance(double amount) {
        super.addBalance(amount);
        if (amount > 6) {
            freeCoffee = true;
        }
    }

    @Override
    public String toString(){
        return "CoffeeVendingMachine at " + getLocation() + " | Cups left: " + coffeeCups;
    }


}
