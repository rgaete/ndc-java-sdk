package org.iata.ndc._2015_2.builder.element;

import org.iata.ndc.schema._2015_2.AgencyIDType;
import org.iata.ndc.schema._2015_2.MsgPartiesType;
import org.iata.ndc.schema._2015_2.ObjectFactory;
import org.iata.ndc.schema._2015_2.TravelAgencySenderType;
import org.iata.ndc.schema._2015_2.MsgPartiesType;

/**
 * Creates new instance of MsgPartiesType.
 * A new instance of the builder can be created each time you need a different party object or you can use the {@link #clear() clear()} method.<br>
 *
 */
public class PartyBuilder {
	private static final ObjectFactory factory = new ObjectFactory();
	private MsgPartiesType party;

	public PartyBuilder() {
		clear();
	}

	/**
	 * Re-initializes builder to empty state.
	 */
	public void clear() {
		party = factory.createMsgPartiesType();
		party.setSender(factory.createMsgPartiesTypeSender());
	}

	/**
	 * Creates TravelAgencySender representation and sets it as request sender.
	 * @param name Travel agency name
	 * @param iataNumber
	 * @param agencyId
	 * @return current builder instance
	 */
	public PartyBuilder setTravelAgencySender(String name, String iataNumber, String agencyId) {
		TravelAgencySenderType agency = factory.createTravelAgencySenderType();
		agency.setName(name);
		agency.setIATANumber(iataNumber);
		AgencyIDType agencyIDType = factory.createAgencyIDType();
		agencyIDType.setValue(agencyId);
		agency.setAgencyID(agencyIDType);
		party.getSender().setTravelAgencySender(agency);
		return this;
	}

	/**
	 * Builds MsgPartiesType instance and returns it.
	 * @return constructed MsgPartiesType instance
	 */
	public MsgPartiesType build() {
		return party;
	}
}
