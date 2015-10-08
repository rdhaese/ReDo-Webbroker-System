package org.realdolmen.webbroker.xml;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Helper class to marshal and unmarshal XML files.
 *
 * @author Youri Flement
 */
@Stateless
@LocalBean
public class XmlSerializer {

    public <T> T unmarshalStream(Class<T> tClass, InputStream stream) throws JAXBException {
        return unmarshal(tClass, new StreamSource(stream));
    }

    public <T> T unmarshal(Class<T> tClass, Source source) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(source, tClass).getValue();
    }

    public <T> void marshal(T element, OutputStream os) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(element.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(element, os);
    }
}
