package com.njanor.deploypipeline.config.eventstore;

import com.esotericsoftware.kryo.Kryo;
import com.njanor.deploypipeline.endringsynskje.EndringsynskjeRegistrertEvent;
import com.njanor.deploypipeline.monitoring.ApplicationHasStartedEvent;
import no.ks.eventstore2.eventstore.KryoClassRegistration;

public class EventStoreClassRegistration implements KryoClassRegistration {

    @Override
    public void registerClasses(Kryo kryo) {
        kryo.register(ApplicationHasStartedEvent.class, 200);

        kryo.register(EndringsynskjeRegistrertEvent.class, 201);
    }
}
