package com.njanor.deploypipeline.endringsynskje;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.njanor.deploypipeline.config.eventstore.Aggregate;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.ask.Asker;
import no.ks.eventstore2.projection.Projection;
import no.ks.eventstore2.projection.Subscriber;

import java.util.ArrayList;
import java.util.List;

@Subscriber(Aggregate.ENDRINGSYNSKJE)
public class EndringsynskjeProjection extends Projection {

    private List<Endringsynskje> endringsynskje = new ArrayList<Endringsynskje>();

    public EndringsynskjeProjection(ActorRef eventStore) {
        super(eventStore);
    }

    public static Props mkProps(ActorRef eventStore) {
        return Props.create(EndringsynskjeProjection.class, eventStore);
    }

    @Handler
    public void handleEvent(EndringsynskjeRegistrertEvent event) {
        endringsynskje.add(new Endringsynskje(event));
    }

    public List<Endringsynskje> getRegistrerteEndringsynskje() {
        return endringsynskje;
    }

    public static List<Endringsynskje> askRegistrerteEndringsynskjer(ActorRef endringsynskjeProjection) {
        try {
            return Asker.askProjection(endringsynskjeProjection, "getRegistrerteEndringsynskje").list(Endringsynskje.class);
        } catch (Exception e) {
            throw new RuntimeException("Kunne ikkje henta endringsynskje");
        }
    }
}
