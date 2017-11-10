package org.iata.ndc._2015_2.builder.element;

import org.iata.ndc.schema._2015_2.DataListType;
import org.iata.ndc.schema._2015_2.DataListType.Flight;
import org.iata.ndc.schema._2015_2.ListOfFlightSegmentType;
import org.iata.ndc.schema._2015_2.ObjectFactory;
import org.iata.ndc.schema._2015_2.OriginDestination;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataListBuilderTest {
	private static final ObjectFactory factory = new ObjectFactory();
	private DataListBuilder testedClass;

	@Before
	public void setUp() {
		testedClass = new DataListBuilder();
	}

	@Test
	public void setFlightSegmentsList() {
		ListOfFlightSegmentType flightSegments = factory.createListOfFlightSegmentType();
		flightSegments.setSegmentKey("test");
		DataListType list = testedClass.setFlightSegmentList(Arrays.asList( new ListOfFlightSegmentType[] {flightSegments})).build();

		assertEquals(flightSegments.getSegmentKey(), list.getFlightSegmentList().get(0).getSegmentKey());
	}

	@Test
	public void setFlightList() {
		Flight flight = factory.createDataListTypeFlight();
		flight.setFlightKey("test");
		DataListType list = testedClass.setFlightList(Arrays.asList( new Flight[] {flight})).build();

		assertEquals(flight.getFlightKey(), list.getFlightList().get(0).getFlightKey());
	}

	@Test
	public void setOriginDestinationList() {
		OriginDestination originDestination = factory.createOriginDestination();
		originDestination.setOriginDestinationKey("test");
		DataListType list = testedClass.setOriginDestinationList(Arrays.asList( new OriginDestination[] {originDestination})).build();

		assertEquals(originDestination.getOriginDestinationKey(), list.getOriginDestinationList().get(0).getOriginDestinationKey());
	}

	@Test
	public void clearResetsFlightSegmentList() {
		Flight flight = factory.createDataListTypeFlight();
		flight.setFlightKey("test");
		testedClass.setFlightList(Arrays.asList( new Flight[] {flight})).clear();
		DataListType list = testedClass.build();

		List<Flight> actual = list.getFlightList();
		assertTrue("Non-empty Flight list after clear", actual == null || actual.isEmpty());
	}

	@Test
	public void clearResetsFlightFlightList() {
		ListOfFlightSegmentType flightSegments = factory.createListOfFlightSegmentType();
		flightSegments.setSegmentKey("test");
		testedClass.setFlightSegmentList(Arrays.asList( new ListOfFlightSegmentType[] {flightSegments})).clear();;
		DataListType list = testedClass.build();

		List<ListOfFlightSegmentType> actual = list.getFlightSegmentList();
		assertTrue("Non-empty FlightSegment list after clear", actual == null || actual.isEmpty());
	}

	@Test
	public void clearResetsOrderDestinationList() {
		OriginDestination originDestination = factory.createOriginDestination();
		originDestination.setOriginDestinationKey("test");
		testedClass.setOriginDestinationList(Arrays.asList( new OriginDestination[] {originDestination})).clear();
		DataListType list = testedClass.build();

		List<OriginDestination> actual = list.getOriginDestinationList();
		assertTrue("Non-empty OriginDestination list after clear", actual == null || actual.isEmpty());
	}

}
