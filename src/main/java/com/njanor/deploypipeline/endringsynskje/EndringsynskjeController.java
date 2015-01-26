package com.njanor.deploypipeline.endringsynskje;

import akka.actor.ActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import javax.annotation.Resource;

import static akka.pattern.Patterns.ask;

@Controller
@RequestMapping("/endringsynskje")
public class EndringsynskjeController{

    private static final Logger LOG = LoggerFactory.getLogger(EndringsynskjeController.class);

    @Resource(name = "commandDispatcher")
    private ActorRef commandDispatcher;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public void registrerEndringsynskje(@RequestBody String namn) {
        LOG.info("Mottatt endringsynskje: " + namn);
        Future<Object> askRegistrerEndringsynskje = ask(commandDispatcher, new RegistrerEndringsynskjeCommand(namn), 1000);
        try {
            Object result = Await.result(askRegistrerEndringsynskje, Duration.create("1000"));
        } catch (Exception e) {
            return;
        }
    }
}
