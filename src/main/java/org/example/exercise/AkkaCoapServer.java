package org.example.exercise;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class AkkaCoapServer {

    public static void main(String[] args) {
        // Initialize Akka Actor System
        ActorSystem system = ActorSystem.create("CoapSystem");
        ActorRef requestHandler = system.actorOf(CoapRequestHandler.props(), "requestHandler");

        // Create CoAP server
        CoapServer server = new CoapServer();

        // Add a resource to the CoAP server
        server.add(new CoapResource("example") {
            @Override
            public void handleGET(CoapExchange exchange) {
                String request = exchange.getRequestText();
                requestHandler.tell(request, ActorRef.noSender());
                exchange.respond("Request received");
            }
        });

        // Start the CoAP server
        server.start();
        System.out.println("CoAP server started");
    }
}
