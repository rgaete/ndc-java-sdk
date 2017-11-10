package org.iata.ndc._2015_2.schema;

import org.iata.ndc.schema._2015_2.ServicePriceRS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ServicePriceRSUnmarshallingIT extends AbstractUnmarshaller<ServicePriceRS> {

	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> sampleFiles() {
		return Arrays.asList(new Object[][] {
			{"/Athena/OneWay/ServicePriceRS.xml", BigDecimal.valueOf(0)},
			{"/Athena/RoundTrip/ServicePriceRS.xml", BigDecimal.valueOf(0)},
			{"/Kronos/OneWay/ServicePriceRS.xml", BigDecimal.valueOf(24)},
			{"/Kronos/RoundTrip/ServicePriceRS.xml", BigDecimal.valueOf(24)}
		});
	}

	@Parameter
	public String resource;

	@Parameter(value=1)
	public BigDecimal expectedAmount;

	@Test
	public void unmarshal() throws JAXBException {
		ServicePriceRS servicePriceRS = unmarshal(resource);
		BigDecimal actual = servicePriceRS.getDataLists().getServiceList().get(0).getPrice().get(0).getTotal().getValue();
		assertEquals(expectedAmount, actual);
	}
}