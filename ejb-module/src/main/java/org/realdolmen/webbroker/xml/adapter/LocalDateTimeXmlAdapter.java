package org.realdolmen.webbroker.xml.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Converter class to marshal/unmarshal a {@link LocalDateTime} to a {@link String}.
 *
 * @author Youri Flement
 */
public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateTimeXmlAdapter.class);

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Converts the input date to a {@link String} with the format "yyyy-MM-dd HH:mm:ss'.
     *
     * @param inputDate The date to convert.
     * @return the {@link String} representation of the date.
     * @throws Exception if the the inputDate is <code>null</code>.
     */
    @Override
    public String marshal(LocalDateTime inputDate) throws Exception {
        return inputDate.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    /**
     * Converts the date {@link String} to a {@link LocalDateTime}. The input date should have the format:
     *
     *      yyyy-MM-dd HH:mm:ss
     *
     * If the input date does not follow this format, a {@link DateTimeParseException} is thrown.
     *
     * @param dateString The date to convert.
     * @return  The converted date.
     * @throws Exception if the input does not follow the date pattern.
     */
    @Override
    public LocalDateTime unmarshal(String dateString) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            LOGGER.warn("Attempting to parse date with wrong format: " + dateString);
            throw e;
        }
    }

}
