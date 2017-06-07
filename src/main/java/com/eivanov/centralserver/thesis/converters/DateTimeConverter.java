/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eivanov.centralserver.thesis.converters;

import com.mongodb.DBObject;
import static javassist.CtMethod.ConstParameter.string;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author killer
 */
public class DateTimeConverter implements Converter<DBObject, DateTime> {

    @Override
    public DateTime convert(DBObject s) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt = formatter.parseDateTime( (String)s.get("timestamp") );
        return dt;
    }

}
