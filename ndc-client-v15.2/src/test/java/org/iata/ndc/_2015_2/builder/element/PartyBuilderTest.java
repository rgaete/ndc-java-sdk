package org.iata.ndc._2015_2.builder.element;

import org.iata.ndc.schema._2015_2.MsgPartiesType;
import org.iata.ndc.schema._2015_2.TravelAgencySenderType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PartyBuilderTest {
    private PartyBuilder testedClass;

    @Before
    public void setUp() {
        testedClass = new PartyBuilder();
    }

    @Test
    public void setTravelAgencySender() {
        MsgPartiesType properties = testedClass.setTravelAgencySender("a", "b", "c").build();
        TravelAgencySenderType sender = properties.getSender().getTravelAgencySender();
        assertEquals("a", sender.getName());
        assertEquals("b", sender.getIATANumber());
        assertEquals("c", sender.getAgencyID().getValue());
    }

    @Test
    public void clearResets() {
        testedClass.setTravelAgencySender("a", "b", "c").clear();
        MsgPartiesType properties = testedClass.build();
        assertNull(properties.getSender().getTravelAgencySender());
    }

}
