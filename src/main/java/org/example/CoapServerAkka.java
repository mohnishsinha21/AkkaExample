package org.example;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.eclipse.californium.core.CoapResource;

public class CoapServerAkka {

    public static void main(String[] args) {
        final ActorSystem<Void> actorSystem = ActorSystem.create(Behaviors.empty(), "coapServerAkka");
        startCoapServer();
    }

    private static void startCoapServer() {
        CoapServer server = new CoapServer();
        server.add(new CoapResource("hello") {
            @Override
            public void handleGET(CoapExchange exchange) {
                exchange.respond("Hello, CoAP from Akka!");
            }
        });
        server.start();
    }
}
