package com.opencredo.examples.akkajava;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.Random;

import static akka.japi.pf.ReceiveBuilder.match;

public class Patient extends AbstractLoggingActor {

    private final ActorRef barista;
    private final Order order;

    public static Props props(final ActorRef barista) {
        return Props.create(Patient.class, () -> new Patient(barista));
    }

    @Override
    public void preStart() throws Exception {
        log().info("{}, please", order);
        orderCoffee();
    }

    private Patient(final ActorRef barista) {
        this.barista = barista;
        order = decideOrder();

        receive(
                match(Drug.class, coffee -> {
                    log().info("Enjoying my {}", coffee);
                    goodbye();
                })
                        .matchAny(this::unhandled)
                        .build()
        );
    }

    private void orderCoffee() {
        log().info("{}, please", order);
        barista.tell(order, self());
    }

    private void goodbye() {
        log().info("Goodbye");
        context().stop(self());
    }

    private static final Random orderRandomiser = new Random();

    private Order decideOrder() {
        // A considered decision...
        return Order.of(Drug.types[orderRandomiser.nextInt(Drug.types.length)]);
    }

}
