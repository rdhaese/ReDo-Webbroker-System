package org.realdolmen.webbroker.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Period;
import java.util.Objects;

/**
 * Converter to convert a {@link java.time.Period} to a {@link String} which is the ISO-8601 representation of the duration.
 *
 * @author Youri Flement
 */
@Converter(autoApply = true)
public class PeriodConverter implements AttributeConverter<Period, String> {

    /**
     * @return an ISO-8601 representation of this duration.
     * @see Period#toString()
     */
    @Override
    public String convertToDatabaseColumn(Period entityValue) {
        return Objects.toString(entityValue, null);
    }

    @Override
    public Period convertToEntityAttribute(String databaseValue) {
        return Period.parse(databaseValue);
    }

}