package com.ccsw.tutorial.common.date;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ParseDate {

    public static Date parseStringToDate(String dateString) throws ParseException {
        System.out.println("\nDATE PASADA Y CASTEO\n" + dateString);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(dateString);
        return new Date(parsedDate.getTime());
    }
}
