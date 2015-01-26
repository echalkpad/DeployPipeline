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
    @Autowired
    private ActorSystem actorSystem;

    @Bean(name = "commandDispatcher")
    public ActorRef getCommandDispatcherRef() {
        List<Props> props = new ArrayList<Props>();

        props.add(EndringsynskjeCommandHandler.mkProps(eventStore));

        return actorSystem.actorOf(CommandDispatcher.mkProps(props), "commandDispatcher");
    }
}
