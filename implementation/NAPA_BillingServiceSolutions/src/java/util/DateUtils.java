package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andreas on 18.06.2017.
 */
public class DateUtils {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date parseDate(String date){

        try {

            return df.parse(date);

        } catch (ParseException e) {
            e.printStackTrace();

            return new Date();
        }
    }

    public static String unixTImestampToString(Long unixTimestamp){

        Date date = new Date(unixTimestamp);

        return df.format(date);
    }

}
