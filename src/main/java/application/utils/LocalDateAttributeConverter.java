package application.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by matan,
 * On 13/12/2016.
 */


@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}