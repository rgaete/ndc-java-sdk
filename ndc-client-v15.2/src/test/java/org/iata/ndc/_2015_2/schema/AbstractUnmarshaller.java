package org.iata.ndc._2015_2.schema;

import org.iata.ndc._2015_2.NdcClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

abstract class AbstractUnmarshaller<T> {

	@SuppressWarnings("unchecked")
	public T unmarshal(String resource) throws JAXBException {
		InputStream inputStream = this.getClass().getResourceAsStream(resource);
		JAXBContext context = NdcClient.getJaxbContext();
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (T) unmarshaller.unmarshal(inputStream);
	}

}
