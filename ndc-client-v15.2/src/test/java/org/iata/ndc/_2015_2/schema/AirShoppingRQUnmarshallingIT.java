package org.iata.ndc.schema;

import org.iata.ndc.schema._2015_2.AirShopReqAttributeQueryTypeOriginDestination;
import org.iata.ndc.schema._2015_2.AirShoppingRQ;
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
public class AirShoppingRQUnmarshallingIT extends AbstractUnmarshaller<AirShoppingRQ> {

	@Parameters(name = "{index}: {0}")
	public static Collection<String[]> sampleFiles() {
		return Arrays.asList(new String[][] {
			{"/Athena/OneWay/AirShoppingRQ - ARN-LHR OneWay with one pax.xml", "LHR"},
			{"/Athena/RoundTrip/AirShoppingRQ - CDG-RIX Round Trip with one pax.xml", "RIX"},
			{"/Kronos/OneWay/AirShoppingRQ - OneWay with one pax.xml", "RIX"},
			{"/Kronos/RoundTrip/AirShoppingRQ - RoundTrip with one pax.xml", "RIX"}
		});
	}

	@Parameter
	public String resource;

	@Parameter(value=1)
	public String arrivalAirport;

	@Test
	public void unmarshal() throws JAXBException {
		AirShoppingRQ object = unmarshal(resource);
		List<AirShopReqAttributeQueryTypeOriginDestination> originDestinations = object.getCoreQuery().getOriginDestinations();
		assertEquals(arrivalAirport, originDestinations.get(0).getArrival().getAirportCode().getValue());
	}
}