package org.example;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class AkkaExample {

    public static void main(String[] args) {
        final ActorSystem<Greet> actorSystem = ActorSystem.create(HelloWorld.create(), "helloAkka");
        actorSystem.tell(new Greet("Akka"));
    }

    public static class Greet {
        public final String name;

        public Greet(String name) {
            this.name = name;
        }
    }

    public static class HelloWorld extends AbstractBehavior<Greet> {

        public static Behavior<Greet> create() {
            return Behaviors.setup(HelloWorld::new);
        }

        private HelloWorld(ActorContext<Greet> context) {
            super(context);
        }

        @Override
        public Receive<Greet> createReceive() {
            return newReceiveBuilder().onMessage(Greet.class, this::onGreet).build();
        }

        private Behavior<Greet> onGreet(Greet command) {
            System.out.println("Hello, " + command.name + "!");
            return this;
        }
    }
}
