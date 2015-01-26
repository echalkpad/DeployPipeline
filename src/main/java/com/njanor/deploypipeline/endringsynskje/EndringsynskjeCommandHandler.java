package com.njanor.deploypipeline.endringsynskje;

import akka.actor.ActorRef;
import akka.actor.Props;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.command.CommandHandler;

public class EndringsynskjeCommandHandler extends CommandHandler {
    public EndringsynskjeCommandHandler(ActorRef eventStore) {
        super(eventStore);
    }

    public static Props mkProps(ActorRef eventstore) {
        return Props.create(EndringsynskjeCommandHandler.class, eventstore);
    }

    @Handler
    public void handleCommand(RegistrerEndringsynskjeCommand command) {
        eventStore.tell(new EndringsynskjeRegistrertEvent(command.getNamn()), self());
    }
}
