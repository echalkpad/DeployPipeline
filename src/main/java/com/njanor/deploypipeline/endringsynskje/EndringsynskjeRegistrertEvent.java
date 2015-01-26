package com.njanor.deploypipeline.endringsynskje;

import com.njanor.deploypipeline.config.eventstore.Aggregate;
import no.ks.eventstore2.Event;
import java.util.UUID;

public class EndringsynskjeRegistrertEvent extends Event {

    //TODO: Generer EndringsynskjeId
    private String endringsynskjeId;
    private String namn;

    public EndringsynskjeRegistrertEvent(String namn) {
        this.endringsynskjeId = UUID.randomUUID().toString();
        this.namn = namn;
    }

    public String getNamn() {
        return namn;
    }

    @Override
    public String getAggregateType() {
        return Aggregate.ENDRINGSYNSKJE;
    }

    @Override
    public String getLogMessage() {
        return "Endringsynskje " + namn + " registrert med ID " + endringsynskjeId;
    }

    @Override
    public String getAggregateRootId() {
        return endringsynskjeId;
    }
}
