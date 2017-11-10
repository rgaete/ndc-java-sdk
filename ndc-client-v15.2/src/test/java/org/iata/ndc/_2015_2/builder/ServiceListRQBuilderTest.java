package org.iata.ndc._2015_2.builder;

import org.iata.ndc.schema._2015_2.ObjectFactory;
import org.iata.ndc.schema._2015_2.ServiceListRQ;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceListRQBuilderTest {
    private static final ObjectFactory factory = new ObjectFactory();
    private ServiceListRQBuilder testedClass;


    @Before
    public void setUp() {
        testedClass = new ServiceListRQBuilder();
    }

    @Test
    public void documentNode() {
        ServiceListRQ request = testedClass.build();
        assertEquals("1.0", request.getDocument().getReferenceVersion());
        assertEquals("NDC ServiceListRQ Message", request.getDocument().getName());
    }

    @Test
    public void setShoppingResponseId() {
        ServiceListRQ request = testedClass.setShoppingResponseId("responseID").build();
        assertEquals("responseID", request.getShoppingResponseIDs().getResponseID().getValue());
    }
}
