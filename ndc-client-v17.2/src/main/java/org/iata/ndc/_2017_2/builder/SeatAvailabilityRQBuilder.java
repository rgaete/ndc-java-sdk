package org.iata.ndc._2017_2.builder;

import org.iata.ndc.schema._2017_2.MsgDocumentType;
import org.iata.ndc.schema._2017_2.MsgPartiesType;
import org.iata.ndc.schema._2017_2.ObjectFactory;
import org.iata.ndc.schema._2017_2.SeatAvailabilityRQ;

/**
 * This class provides a simple way to create SeatAvailabilityRQ objects. It implements fluent interface, thus allowing to chain methods.<br>
 * Since the object returned by {@link #build build()} can be modified any further customization can be performed manually.
 */
public class SeatAvailabilityRQBuilder {
    private static final ObjectFactory factory = new ObjectFactory();

    private SeatAvailabilityRQ request;

    private MsgPartiesType party;

    /**
     * Creates a new instance of SeatAvailabilityRQBuilder.
     * A new instance can be created for each request or you can use the {@link #clear() clear()} method.<br>
     * <p>
     * Defaults:<ol>
     * <li> One adult traveler.
     * </ol>
     */
    public SeatAvailabilityRQBuilder() {
        clear();
    }

    /**
     * Re-initializes builder to empty state.
     */
    public void clear() {

        request = factory.createSeatAvailabilityRQ();
        request.setQuery(factory.createSeatAvailabilityRQQuery());
        party = null;
    }

    /**
     * Sets a pre-built MsgPartiesType object to the request.
     *
     * @param party object which represents Party node
     * @return current builder instance
     */
    public SeatAvailabilityRQBuilder setParty(MsgPartiesType party) {
        request.setParty(party);
        return this;
    }

    /**
     * Builds SeatAvailabilityRQ instance and returns it.
     *
     * @return constructed SeatAvailabilityRQ instance
     */
    public SeatAvailabilityRQ build() {
        request.setVersion("1.1.5");
        addDocumentNode();

        return request;
    }

    private void addDocumentNode() {
        MsgDocumentType document = factory.createMsgDocumentType();
        document.setName("NDC SeatAvailabilityRQ Message");
        document.setReferenceVersion("1.0");
        request.setDocument(document);
    }
}
