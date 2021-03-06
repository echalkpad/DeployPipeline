package com.njanor.deploypipeline.config.eventstore;

import static akka.pattern.Patterns.ask;
import static no.ks.eventstore2.projection.CallProjection.call;

import javax.annotation.Resource;

import com.njanor.deploypipeline.endringsynskje.EndringsynskjeProjection;
import no.ks.eventstore2.projection.Projection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;

import com.njanor.deploypipeline.monitoring.ApplicationStatusProjection;
import com.njanor.deploypipeline.util.Timeout;

@Configuration
public class ProjectionInitializer {

    @Resource(name = "projectionManager")
    private ActorRef projectionManager;


    @Bean(name = "applicationStatusProjection")
    public ActorRef getApplicationStatusProjectionRef() {
        return askProjectionRef(ApplicationStatusProjection.class);
    }

    @Bean(name = "endringsynskjeProjection")
    public ActorRef getEndringsynskjeProjection() {
        return askProjectionRef(EndringsynskjeProjection.class);
    }

    private ActorRef askProjectionRef(Class<? extends Projection> projectionClass) {
        Future<Object> getProjection = ask(projectionManager, call("getProjectionRef", projectionClass), Timeout.THREE_SECONDS);
        try {
            return (ActorRef) Await.result(getProjection, Duration.create(Timeout.THREE_SECONDS_STR));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving projection reference", e);
        }
    }
}
