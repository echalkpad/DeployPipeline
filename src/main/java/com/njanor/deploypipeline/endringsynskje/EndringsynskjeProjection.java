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
import java.util.stream.Collectors;

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

    public Endringsynskje getEndringsynskjeMed(String namn) {
        List<Endringsynskje> endringsynskjelista =  endringsynskje.stream().filter(e -> e.getNamn().equalsIgnoreCase(namn)).collect(Collectors.toList());
        if (endringsynskjelista.size() > 1) {
            throw new RuntimeException("Fleire endringsynskje med same namn registrert");
        }
        if (endringsynskjelista.size() == 1) {
            return endringsynskjelista.get(0);
        }
        return null;
    }

    public static Endringsynskje askEndringsynskjeMed(String namn, ActorRef endringsynskjeProjection) {
        try {
            return Asker.askProjection(endringsynskjeProjection, "getEndringsynskjeMed", namn).single(Endringsynskje.class);
        } catch (Exception e) {
            throw new RuntimeException("Kunne ikkje henta endringsynskje");
        }
    }
}
