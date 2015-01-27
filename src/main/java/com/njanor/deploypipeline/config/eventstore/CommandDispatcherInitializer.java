package com.njanor.deploypipeline.config.eventstore;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.njanor.deploypipeline.endringsynskje.EndringsynskjeCommandHandler;
import no.ks.eventstore2.command.CommandDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
public class CommandDispatcherInitializer {

    @Resource(name = "eventStore")
    private ActorRef eventStore;

    @Resource(name = "endringsynskjeProjection")
    private ActorRef endringsynskjeProjection;

    @Autowired
    private ActorSystem actorSystem;

    @Bean(name = "commandDispatcher")
    public ActorRef getCommandDispatcherRef() {
        List<Props> props = new ArrayList<Props>();

        props.add(EndringsynskjeCommandHandler.mkProps(eventStore, endringsynskjeProjection));

        return actorSystem.actorOf(CommandDispatcher.mkProps(props), "commandDispatcher");
    }
}
