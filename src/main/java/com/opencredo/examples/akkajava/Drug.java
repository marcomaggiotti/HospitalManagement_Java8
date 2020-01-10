package com.opencredo.examples.akkajava;

public class Drug {
    public static enum DrugType {
        As, // Aspirin
        An, // Healthy
        I, // Insulin
        P // Paracetamol  
        
    }

    public static final CoffeeType[] types = CoffeeType.values();

    public final CoffeeType type;

    private Drug(final CoffeeType type) {
        this.type = type;
    }

    public static Drug ofType(CoffeeType type) {
        return new Drug(type);
    }

    @Override
    public String toString() {
        return type.name();
    }
}
