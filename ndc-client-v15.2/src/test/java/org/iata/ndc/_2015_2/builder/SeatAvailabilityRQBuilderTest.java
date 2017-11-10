package org.iata.ndc._2015_2.builder;

import org.iata.ndc.schema._2015_2.*;
import org.iata.ndc.schema._2015_2.DataListType.Flight;
import org.iata.ndc.schema._2015_2.MsgPartiesType.Participants;
import org.iata.ndc.schema._2015_2.MsgPartiesType.Participants.Participant;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SeatAvailabilityRQBuilderTest {
	private static final ObjectFactory factory = new ObjectFactory();
	private SeatAvailabilityRQBuilder testedClass;


	@Before
	public void setUp() {
		testedClass = new SeatAvailabilityRQBuilder();
	}

	@Test
	public void documentNode() {
		SeatAvailabilityRQ request = testedClass.build();
		assertEquals("1.0", request.getDocument().getReferenceVersion());
		assertEquals("NDC SeatAvailabilityRQ Message", request.getDocument().getName());
	}

	@Test
	public void setParty() {
		MsgPartiesType party = Initializer.getObject(MsgPartiesType.class);
		Participants participants = factory.createMsgPartiesTypeParticipants();
		party.setParticipants(new JAXBElement<MsgPartiesType.Participants>(new QName("Participants"), Participants.class, participants));
		Participant participant = Initializer.getObject(Participant.class);
		EnabledSysParticipantType enabled = factory.createEnabledSysParticipantType();
		enabled.setName("Enabled System");
		participant.setEnabledSystemParticipant(enabled);
		party.getParticipants().getValue().getParticipant().add(participant);
		SeatAvailabilityRQ request = testedClass.setParty(party).build();
		assertEquals("Enabled System", request.getParty().getParticipants().getValue().getParticipant().get(0).getEnabledSystemParticipant().getName());
	}

	@Test
	public void addDataList() {
		DataListType dataList = factory.createDataListType();
		Flight flight = factory.createDataListTypeFlight();
		flight.setFlightKey("test");
		dataList.setFlightList( Arrays.asList( new Flight[] {flight}));

		SeatAvailabilityRQ request = testedClass.setDataList(dataList).build();

		assertEquals("test", request.getDataList().getFlightList().get(0).getFlightKey());
	}



}
