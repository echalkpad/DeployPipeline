package com.njanor.deploypipeline.endringsynskje;

import akka.testkit.TestActorRef;
import com.njanor.deploypipeline.SystemTestKit;
import com.njanor.deploypipeline.testdatabuilders.endringsynskje.EndringsynskjeRegistrertEventBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EndringsynskjeProjectionTest extends SystemTestKit {

    private TestActorRef<EndringsynskjeProjection> projection;

    @Before
    public void setUp() {
        projection = createEndringsynskjeProjection();
    }

    @Test
    public void hentAlleEndringsynskjeNaarDetIkkjeErNokonTest() {
        assertEquals(0, EndringsynskjeProjection.askRegistrerteEndringsynskjer(projection).size());
    }

    @Test
    public void hentAlleEndringsynskjeNaarDetErEitTest() {
        EndringsynskjeRegistrertEvent event = new EndringsynskjeRegistrertEventBuilder().build();
        projection.tell(event, super.testActor());

        assertEquals(1, EndringsynskjeProjection.askRegistrerteEndringsynskjer(projection).size());
        assertEquals(event.getNamn(), EndringsynskjeProjection.askRegistrerteEndringsynskjer(projection).get(0).getNamn());
    }

    @Test
    public void hentEndringsynskjePaaNamn() {
        EndringsynskjeRegistrertEvent event = new EndringsynskjeRegistrertEventBuilder().build();
        projection.tell(event, super.testActor());
        projection.tell(new EndringsynskjeRegistrertEventBuilder().medNamn(event.getNamn() + "TOILL").build(), super.testActor());

        Endringsynskje endringsynskje = EndringsynskjeProjection.askEndringsynskjeMed(event.getNamn(), projection);
        assertNotNull(endringsynskje);
        assertEquals(event.getNamn(), endringsynskje.getNamn());
    }
}
