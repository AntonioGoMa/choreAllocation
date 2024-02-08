package util.functions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String format(Date date) {
        SimpleDateFormat formatted = new SimpleDateFormat("dd-MM-yy");
        return formatted.format(date);
    }
}
