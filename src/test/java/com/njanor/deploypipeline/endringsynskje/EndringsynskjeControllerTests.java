package com.njanor.deploypipeline.endringsynskje;

import akka.testkit.TestActorRef;
import com.njanor.deploypipeline.SystemTestKit;
import no.ks.eventstore2.testkit.EventReceiver;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

public class EndringsynskjeControllerTests extends SystemTestKit {
    @Test
    public void TestRegistrerEndringsynskje() {
        EndringsynskjeController controller = new EndringsynskjeController();
        EventReceiver eventReceiver = createEventReceiver();
        TestActorRef<EndringsynskjeCommandHandler> commandHandler = createEndringsynskjeCommandHandler(eventReceiver.self());

        ReflectionTestUtils.setField(controller, "commandDispatcher", commandHandler);

        final String namn = "TestNamnetMitt";
        controller.registrerEndringsynskje(namn);

        assertEquals(1, eventReceiver.receivedEvents.size());
        assertEquals(EndringsynskjeRegistrertEvent.class, eventReceiver.receivedEvents.get(0).getClass());
        assertEquals(namn, ((EndringsynskjeRegistrertEvent)eventReceiver.receivedEvents.get(0)).getNamn());
    }
}
