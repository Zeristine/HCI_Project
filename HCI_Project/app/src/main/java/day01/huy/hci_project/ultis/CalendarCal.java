package day01.huy.hci_project.ultis;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

public class CalendarCal {

    @NotNull
    public static String getCardAge(@NotNull Date createdDate) {
        Calendar calendar = Calendar.getInstance();
        Date current = calendar.getTime();
        int seconds = 0, minutes = 0, hours = 0;
        String secValue = "", minValue = "", hourValue = "";
        String value = "";

        seconds = current.getSeconds() - createdDate.getSeconds();
        secValue = seconds + " giây";
        if (seconds >= 60) {
            minutes = seconds / 60;
            secValue = (seconds % 60) + " giây";
            minValue = minutes + " phút";
            if (minutes >= 60) {
                hours = minutes / 60;
                secValue = "";
                minValue = (minutes % 60) + " phút";
                hourValue = hours + " tiếng";
            }
        }
        if(hours > 2){
            minValue = "";
        }
        value = hourValue + minValue + secValue;
        return value;
    }

}
