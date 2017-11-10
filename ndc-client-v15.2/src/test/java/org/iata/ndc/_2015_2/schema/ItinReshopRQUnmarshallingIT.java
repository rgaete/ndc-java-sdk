package org.iata.ndc.schema;

import org.iata.ndc.schema._2015_2.FlightType;
import org.iata.ndc.schema._2015_2.ItinReshopRQ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ItinReshopRQUnmarshallingIT extends AbstractUnmarshaller<ItinReshopRQ> {

	@Parameters(name = "{index}: {0}")
	public static Collection<String[]> sampleFiles() {
		return Arrays.asList(new String[][] {
			{"/Athena/OneWay/ItineraryReshopRQ - OneWay.xml", "ARN"},
			{"/Athena/RoundTrip/ItineraryReshopRQ - Round Trip with one pax.xml", "CDG"},
			{"/Kronos/OneWay/ItineraryReshopRQ - OneWay.xml", "ARN"},
			{"/Kronos/RoundTrip/ItineraryReshopRQ - RoundTrip.xml", "ARN"},
		});
	}

	@Parameter
	public String resource;

	@Parameter(value=1)
	public String departure;

	@Test
	public void unmarshal() throws JAXBException {
		ItinReshopRQ itinReshopRQ = unmarshal(resource);
		List<FlightType> originDestination = itinReshopRQ.getQuery().getReshop().get(0).getOrderItems().getOrderItem().get(0).getFlightItem().getOriginDestination();
		assertEquals(departure, originDestination.get(0).getFlight().get(0).getDeparture().getAirportCode().getValue());
	}
}