package com.njanor.deploypipeline.monitoring;

import com.njanor.deploypipeline.config.eventstore.Aggregate;

import no.ks.eventstore2.Event;

public class ApplicationHasStartedEvent extends Event {
    private static final long serialVersionUID = 1L;

    @Override
    public String getAggregateType() {
        return Aggregate.SYSTEM;
    }

    @Override
    public String getLogMessage() {
        return "Application has started";
    }

    @Override
    public String getAggregateRootId() {
        return "ApplicationHasStartedEvent";
    }

}
