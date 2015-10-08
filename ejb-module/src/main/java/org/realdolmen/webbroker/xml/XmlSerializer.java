package org.realdolmen.webbroker.xml;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

@Stateless
@LocalBean
public class XmlSerializer {

    public <T> T unmarhal(Class<T> tClass, InputStream source) throws JAXBException {
        return unmarshal(tClass, new StreamSource(source));
    }

    public <T> T unmarshal(Class<T> tClass, Source source) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(source, tClass).getValue();
    }
}
