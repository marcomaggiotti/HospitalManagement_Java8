package com.opencredo.examples.akkajava;

public class Order {
    public final Drug.DrugType type;

    private Order(final Drug.DrugType type) {
        this.type = type;
    }

    public static Order of(Drug.DrugType type) {
        return new Order(type);
    }

    @Override
    public String toString() {
        return type.name();
    }
}
