package org.example.exercise;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CoapRequestHandler extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(CoapRequestHandler.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, this::onCoapRequest)
                .build();
    }

    private void onCoapRequest(String request) {
        System.out.println("Received coap request");
        log.info("Received CoAP request: {}", request);
        // Process the CoAP request here
    }
}
