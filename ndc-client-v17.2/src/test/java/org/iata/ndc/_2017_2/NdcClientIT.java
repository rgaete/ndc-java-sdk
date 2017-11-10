package org.iata.ndc._2017_2;

import org.iata.ndc._2017_2.builder.AirShoppingRQBuilder;
import org.iata.ndc._2017_2.builder.ServiceListRQBuilder;
import org.iata.ndc._2017_2.builder.element.PartyBuilder;
import org.iata.ndc.schema._2017_2.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NdcClientIT {
    private static final Logger LOG = LoggerFactory.getLogger(NdcClientIT.class);
    private final String server = System.getProperty("server.url");
    private final String apiKey = System.getProperty("api.key");
    private final NdcClient client = new NdcClient(server, apiKey);

    private MsgPartiesType party;
    private static AirShoppingRS airSoppingRS;

    @Before
    public void before() {
        party = new PartyBuilder()
                .setTravelAgencySender("Test sender", "00002004", "test")
                .build();

    }

    @Test
    public void a_serverIsSet() {
        if (server == null) {
            String msg = "System property server.uri is not set.";
            LOG.error(msg);
            fail(msg);
        }
    }

    @Test
    public void b_apiKeyIsSet() {
        if (server == null) {
            String msg = "System property api.key is not set.";
            LOG.error(msg);
            fail(msg);
        }
    }

    @Test
    public void c_existingAirShoppingRQ() throws JAXBException {
        InputStream is = this.getClass().getResourceAsStream("/Kronos/OneWay/AirShoppingRQ - OneWay with one pax.xml");
        JAXBContext context = JAXBContext.newInstance("org.iata.ndc.schema");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        AirShoppingRQ airShoppingRQ = (AirShoppingRQ) unmarshaller.unmarshal(is);

        AirShoppingRS response = null;
        try {
            response = client.airShopping(airShoppingRQ);
        } catch (IOException e) {
            LOG.error("Unexpected exception encountered during service call", e);
            fail(e.toString());
        }
        assertNotNull(response.getSuccess());
    }

    @Test
    public void d_builtAirShoppingRQ() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2015, 10, 19);
        Date date = cal.getTime();

        AirShoppingRQ request = new AirShoppingRQBuilder()
                .setParty(party)
                .addOriginDestination("CDG", "LHR", date, 3, 3)
                .addAirlinePreference("C9")
                .addCabinPreference("M")
                .addFarePreference("759")
                .build();

        AirShoppingRS response = null;
        try {
            response = client.airShopping(request);
        } catch (IOException e) {
            LOG.error("Unexpected exception encountered during service call", e);
            fail(e.toString());
        }
        airSoppingRS = response;
        assertNotNull(response.getSuccess());
    }

    @Test
    public void f_serviceListRQ() {
        String shoppingResponseId = airSoppingRS.getShoppingResponseID().getResponseID().getValue();
        ServiceListRQ request = new ServiceListRQBuilder()
                .setParty(party)
                .setShoppingResponseId(shoppingResponseId)
                .build();
        ServiceListRS response = null;
        try {
            response = client.serviceList(request);
        } catch (IOException e) {
            LOG.error("Unexpected exception encountered during service call", e);
            fail(e.toString());
        }
        processErrors(response.getErrors());
        assertNotNull(response.getSuccess());
    }

    private void processErrors(List<ErrorType> errors) {
        if (errors == null || errors.isEmpty()) {
            return;
        }
        String message = "";
        for (ErrorType e : errors) {
            if (e.getValue() != null && !e.getValue().isEmpty()) {
                message += e.getValue();
                LOG.error(e.getValue());
            }
            if (e.getShortText() != null && !e.getShortText().isEmpty()) {
                message += e.getShortText();
                LOG.error(e.getShortText());
            }
        }
        fail("Server returned errors");
    }

}
