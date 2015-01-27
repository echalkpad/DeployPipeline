package com.njanor.deploypipeline.endringsynskje;

import akka.testkit.TestActorRef;
import com.njanor.deploypipeline.SystemTestKit;
import com.njanor.deploypipeline.testdatabuilders.endringsynskje.EndringsynskjeRegistrertEventBuilder;
import com.njanor.deploypipeline.testdatabuilders.endringsynskje.RegistrerEndringsynskjeCommandBuilder;
import no.ks.eventstore2.testkit.EventReceiver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndringsynskjeCommandHandlerTest extends SystemTestKit {

    private EventReceiver receiver;
    private TestActorRef<EndringsynskjeProjection> endringsynskjeProjection;
    private TestActorRef<EndringsynskjeCommandHandler> commandHandler;

    @Before
    public void setUp() {
        receiver = createEventReceiver();
        endringsynskjeProjection = createEndringsynskjeProjection();
        commandHandler = createEndringsynskjeCommandHandler(receiver.self(), endringsynskjeProjection);
    }

    @Test
    public void registrerEndringsynskjeTest() {
        RegistrerEndringsynskjeCommand command = new RegistrerEndringsynskjeCommandBuilder().build();

        commandHandler.tell(command, super.testActor());

        assertEquals(1, receiver.receivedEvents.size());
        assertEquals(EndringsynskjeRegistrertEvent.class, receiver.receivedEvents.get(0).getClass());
        assertEquals(command.getNamn(), ((EndringsynskjeRegistrertEvent)receiver.receivedEvents.get(0)).getNamn());
    }

    @Test
    public void testAtEinIkkjeKanRegistreraToEndringsynskjeMedSameNamn() {
        EndringsynskjeRegistrertEvent event = new EndringsynskjeRegistrertEventBuilder().build();
        endringsynskjeProjection.tell(event, super.testActor());

        RegistrerEndringsynskjeCommand command = new RegistrerEndringsynskjeCommandBuilder().medNamn(event.getNamn()).build();
        commandHandler.tell(command, super.testActor());

        assertEquals(0, receiver.receivedEvents.size());
    }
}