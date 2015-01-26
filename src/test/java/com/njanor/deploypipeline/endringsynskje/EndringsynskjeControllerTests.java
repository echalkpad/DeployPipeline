package com.njanor.deploypipeline.endringsynskje;

import akka.testkit.TestActorRef;
import com.njanor.deploypipeline.SystemTestKit;
import com.njanor.deploypipeline.testdatabuilders.endringsynskje.EndringsynskjeRegistrertEventBuilder;
import no.ks.eventstore2.testkit.EventReceiver;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class EndringsynskjeControllerTests extends SystemTestKit {

    private EndringsynskjeController controller;

    @Before
    public void setUp() {
        controller = new EndringsynskjeController();
    }

    @Test
    public void testRegistrerEndringsynskje() {
        EventReceiver eventReceiver = createEventReceiver();
        TestActorRef<EndringsynskjeCommandHandler> commandHandler = createEndringsynskjeCommandHandler(eventReceiver.self());

        ReflectionTestUtils.setField(controller, "commandDispatcher", commandHandler);

        final String namn = "TestNamnetMitt";
        controller.registrerEndringsynskje(namn);

        assertEquals(1, eventReceiver.receivedEvents.size());
        assertEquals(EndringsynskjeRegistrertEvent.class, eventReceiver.receivedEvents.get(0).getClass());
        assertEquals(namn, ((EndringsynskjeRegistrertEvent)eventReceiver.receivedEvents.get(0)).getNamn());
    }

    @Test
    public void testHentAlleEndringsynskje() {
        TestActorRef<EndringsynskjeProjection> endringsynskjeProjection = createEndringsynskjeProjection();
        EndringsynskjeRegistrertEvent event = new EndringsynskjeRegistrertEventBuilder().build();
        endringsynskjeProjection.tell(event, super.testActor());
        ReflectionTestUtils.setField(controller, "endringsynskjeProjection", endringsynskjeProjection);
        assertEquals(1, controller.hentEndringsynskje().size());
        assertEquals(event.getNamn(), controller.hentEndringsynskje().get(0).getNamn());
    }
}
