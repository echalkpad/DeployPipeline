package com.njanor.deploypipeline.endringsynskje;

import akka.testkit.TestActorRef;
import com.njanor.deploypipeline.SystemTestKit;
import com.njanor.deploypipeline.testdatabuilders.endringsynskje.RegistrerEndringsynskjeCommandBuilder;
import no.ks.eventstore2.testkit.EventReceiver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndringsynskjeCommandHandlerTest extends SystemTestKit {
    @Test
    public void RegistrerEndringsynskjeTest(){
        EventReceiver receiver = createEventReceiver();
        TestActorRef<EndringsynskjeCommandHandler> commandHandler = createEndringsynskjeCommandHandler(receiver.self());

        RegistrerEndringsynskjeCommand command = new RegistrerEndringsynskjeCommandBuilder().build();

        commandHandler.tell(command, super.testActor());

        assertEquals(1, receiver.receivedEvents.size());
        assertEquals(EndringsynskjeRegistrertEvent.class, receiver.receivedEvents.get(0).getClass());
        assertEquals(command.getNamn(), ((EndringsynskjeRegistrertEvent)receiver.receivedEvents.get(0)).getNamn());
    }
}