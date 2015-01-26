package com.njanor.deploypipeline;

import akka.actor.ActorRef;
import com.njanor.deploypipeline.endringsynskje.EndringsynskjeCommandHandler;
import com.njanor.deploypipeline.endringsynskje.EndringsynskjeProjection;
import no.ks.eventstore2.eventstore.CompleteSubscriptionRegistered;
import akka.actor.ActorSystem;
import akka.testkit.TestActorRef;
import akka.testkit.TestKit;

import com.njanor.deploypipeline.monitoring.ApplicationStatusProjection;
import com.njanor.deploypipeline.util.IdUtil;
import no.ks.eventstore2.testkit.EventStoreTestKit;

public class SystemTestKit extends EventStoreTestKit {

    protected static final ActorSystem actorSystem = ActorSystem.create("testSystem");

    protected TestActorRef<ApplicationStatusProjection> createApplicationStatusProjection() {
        TestActorRef<ApplicationStatusProjection> projection = TestActorRef.create(actorSystem, ApplicationStatusProjection.mkProps(testActor()), IdUtil.createUUID());
        projection.tell(new CompleteSubscriptionRegistered(null), testActor());
        return projection;
    }

    protected TestActorRef<EndringsynskjeCommandHandler> createEndringsynskjeCommandHandler(ActorRef eventstore) {
        return createCommandHandler(EndringsynskjeCommandHandler.mkProps(eventstore));
    }

    protected TestActorRef<EndringsynskjeProjection> createEndringsynskjeProjection() {
        return createProjectionRef(EndringsynskjeProjection.mkProps(testActor()));
    }
}
