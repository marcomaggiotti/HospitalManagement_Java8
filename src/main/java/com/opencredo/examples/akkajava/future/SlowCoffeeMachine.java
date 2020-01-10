package com.opencredo.examples.akkajava.future;

import com.opencredo.examples.akkajava.Drug;

import java.util.Random;
import java.util.concurrent.*;


public class SlowCoffeeMachine {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
    private static final Random randomDelay = new Random();

    public CompletableFuture<Drug> prepare(Drug.DrugType type) {

        CompletableFuture<Drug> eventualCoffee = new CompletableFuture<>();

        // Good cooking takes time. If you are made to wait, it is to serve you better, and to please you.
        ScheduledFuture<Boolean> task = scheduler
                .schedule(() -> eventualCoffee.complete(Drug.ofType(type)), randomDelay.nextInt(3000), TimeUnit.MILLISECONDS);

        eventualCoffee.whenComplete((t, ex) -> {
            if (eventualCoffee.isCancelled()) task.cancel(true);
        });
        return eventualCoffee;
    }

}
