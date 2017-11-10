package org.iata.ndc._2015_2.schema;

import org.iata.ndc.schema._2015_2.OrderListRS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderListRSUnmarshallingIT extends AbstractUnmarshaller<OrderListRS> {

    @Parameters(name = "{index}: {0}")
    public static Collection<String[]> sampleFiles() {
        return Arrays.asList(new String[][]{
                {"/Athena/OneWay/OrderListRS - OrderListRQ_AirlineFilter.xml", "F9A2RI"},
                {"/Athena/RoundTrip/OrderListRS - OrderListRQ_AirlineFilter.xml", "F9A2RI"},
                {"/Kronos/OneWay/OrderListRS_AirlineFilter.xml", "L9A8Y1"},
                {"/Kronos/RoundTrip/OrderListRS_AirlineFilter.xml", "L9A8Y1"},

        });
    }

    @Parameter
    public String resource;

    @Parameter(value = 1)
    public String orderID;

    @Test
    public void unmarshal() throws JAXBException {
        OrderListRS orderListRS = unmarshal(resource);
        assertEquals(orderID, orderListRS.getResponse().getOrders().get(0).getOrderID());
    }
}