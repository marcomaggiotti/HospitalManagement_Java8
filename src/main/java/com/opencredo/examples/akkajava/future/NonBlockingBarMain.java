package com.opencredo.examples.akkajava.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.opencredo.examples.akkajava.Patient;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class NonBlockingBarMain {
    public static void main(String[] args) throws Exception {
        final Config config = ConfigFactory.load();
        final ActorSystem system = ActorSystem.create("NonBlockingBarSystem", config);
        try {
            // Create a Barista
            ActorRef barista = system.actorOf(NonBlockingBarista.props(), "FastBarista");

            // Spawn customers
            system.actorOf(Patient.props(barista), "Alice");
            system.actorOf(Patient.props(barista), "Bob");
            system.actorOf(Patient.props(barista), "Charlie");

        } catch (Exception e) {
            system.terminate();
            throw e;
        }
    }
}
