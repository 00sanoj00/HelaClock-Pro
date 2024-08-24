package lk.sanoj.helaclok.pro.HelaClockPro.Models;

import android.util.Log;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Clock {
    String hours;
    String minutes;
    String meridiem;

    public Clock() {
        updateTime();
    }

    public Time getTime() {
        return new Time(hours, minutes, meridiem);
    }

    public void updateTime() {
        LocalTime now;
        DateTimeFormatter hourFormatter;
        DateTimeFormatter minuteFormatter;
        DateTimeFormatter amPmFormatter;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalTime.now();
            hourFormatter = DateTimeFormatter.ofPattern("hh", Locale.US);
            minuteFormatter = DateTimeFormatter.ofPattern("mm", Locale.US);
            amPmFormatter = DateTimeFormatter.ofPattern("a", Locale.US);

            hours = now.format(hourFormatter);
            minutes = now.format(minuteFormatter);
            meridiem = now.format(amPmFormatter);
        }
    }

    public void setTime(String time) {
        hours = time.substring(0, 2);
        minutes = time.substring(3, 5);
        meridiem = time.substring(6);
        Log.d("setTime", "setTime: " + hours + ":" + minutes + ":" + meridiem);
    }
}
