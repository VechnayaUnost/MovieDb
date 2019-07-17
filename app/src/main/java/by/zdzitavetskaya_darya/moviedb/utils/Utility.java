package by.zdzitavetskaya_darya.moviedb.utils;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utility {

    public static String getFormatDate(final String date) {
        @SuppressLint("SimpleDateFormat") final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (date != null) {
            try {
                final Date newDate = dateFormat.parse(date);
                return DateFormat.format("dd/MM/y", newDate).toString();
            } catch (final ParseException e) {
                e.printStackTrace();
            }
        }

        return date;
    }
}
