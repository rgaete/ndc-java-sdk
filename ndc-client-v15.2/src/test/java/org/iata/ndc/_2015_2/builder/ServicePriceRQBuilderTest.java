package org.iata.ndc._2015_2.builder;

import org.iata.ndc.schema._2015_2.ObjectFactory;
import org.iata.ndc.schema._2015_2.ServicePriceRQ;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServicePriceRQBuilderTest {
    private static final ObjectFactory factory = new ObjectFactory();
    private ServicePriceRQBuilder testedClass;


    @Before
    public void setUp() {
        testedClass = new ServicePriceRQBuilder();
    }

    @Test
    public void documentNode() {
        ServicePriceRQ request = testedClass.build();
        assertEquals("1.0", request.getDocument().getReferenceVersion());
        assertEquals("NDC ServicePriceRQ Message", request.getDocument().getName());
    }

    @Test
    public void setShoppingResponseId() {
        ServicePriceRQ request = testedClass.setShoppingResponseId("responseID").build();
        assertEquals("responseID", request.getShoppingResponseIDs().getResponseID().getValue());
    }
}
