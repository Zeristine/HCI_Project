package day01.huy.hci_project.ultis;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

public class CalendarCal {

    @NotNull
    public static String getCardAge(@NotNull Date createdDate) {
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();
        int seconds, minutes, hours;
        String secValue = "", minValue = "", hourValue = "";

        seconds = current.getSeconds() - createdDate.getSeconds();
        secValue = seconds + "g";
        if (seconds >= 60) {
            minutes = seconds / 60;
            secValue = (seconds % 60) + "g";
            minValue = minutes + "ph";
            if (minutes >= 60) {
                hours = minutes / 60;
                minValue = (minutes % 60) + "ph";
                hourValue = hours + "h";
            }
        }
        return hourValue + minValue + secValue;
    }

}
