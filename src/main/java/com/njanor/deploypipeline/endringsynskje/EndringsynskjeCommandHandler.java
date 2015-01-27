package com.njanor.deploypipeline.endringsynskje;

import akka.actor.ActorRef;
import akka.actor.Props;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.command.CommandHandler;

import javax.annotation.Resource;

public class EndringsynskjeCommandHandler extends CommandHandler {

    private ActorRef endringsynskjeProjection;

    public EndringsynskjeCommandHandler(ActorRef eventStore, ActorRef endringsynskjeProjection) {
        super(eventStore);
        this.endringsynskjeProjection = endringsynskjeProjection;
    }

    public static Props mkProps(ActorRef eventstore, ActorRef endringsynskjeProjection) {
        return Props.create(EndringsynskjeCommandHandler.class, eventstore, endringsynskjeProjection);
    }

    @Handler
    public void handleCommand(RegistrerEndringsynskjeCommand command) {
        if (EndringsynskjeProjection.askEndringsynskjeMed(command.getNamn(), endringsynskjeProjection) == null) {
            eventStore.tell(new EndringsynskjeRegistrertEvent(command.getNamn()), self());
        }
    }
}
