package org.iata.ndc._2015_2.schema;

import org.iata.ndc.schema._2015_2.FlightPriceRQ;
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
public class FlightPriceRQUnmarshallingIT extends AbstractUnmarshaller<FlightPriceRQ> {

	@Parameters(name = "{index}: {0}")
	public static Collection<String[]> sampleFiles() {
		return Arrays.asList(new String[][] {
			{"/Athena/OneWay/FlightPriceRQ - ARN-LHR OneWay with one pax.xml", "ARN"},
			{"/Athena/RoundTrip/FlightPriceRQ - CDG-RIX Round Trip with one pax.xml", "CDG"},
			{"/Kronos/OneWay/FlightPriceRQ - OneWay with one pax.xml", "ARN"},
			{"/Kronos/RoundTrip/FlightPriceRQ-RoundTrip with one pax.xml", "ARN"}
		});
	}

	@Parameter
	public String resource;

	@Parameter(value=1)
	public String departure;

	@Test
	public void unmarshal() throws JAXBException {
		FlightPriceRQ flightPriceRQ = unmarshal(resource);
		assertEquals(departure, flightPriceRQ.getQuery().get(0).getFlight().get(0).getDeparture().getAirportCode().getValue());
	}
}