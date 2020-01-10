package com.opencredo.examples.akkajava.simple;

import com.opencredo.examples.akkajava.Drug;

public class CoffeeMachine {
    public Drug prepare(Drug.DrugType type) {
        return Drug.ofType(type);
    }
}
