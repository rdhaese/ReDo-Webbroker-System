package org.realdolmen.webbroker.converter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public String marshal(LocalDateTime arg0) throws Exception {
        return arg0.format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"));
    }

    @Override
    public LocalDateTime unmarshal(String arg) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(arg, formatter);
    }

}
