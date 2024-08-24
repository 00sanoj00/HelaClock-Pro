package lk.sanoj.helaclok.pro.HelaClockPro.Models;

public class Time {

    private final String hours;
    private final String minutes;
    private final String meridiem;

    public Time(String hours, String minutes, String meridiem) {
        this.hours = hours;
        this.minutes = minutes;
        this.meridiem = meridiem;
    }

    public String getHours() {
        return hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getMeridiem() {
        return meridiem;
    }
}
