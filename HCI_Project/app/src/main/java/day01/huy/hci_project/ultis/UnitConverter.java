package day01.huy.hci_project.ultis;

import android.content.Context;
import android.util.TypedValue;

import org.jetbrains.annotations.NotNull;

public class UnitConverter {

    public static int getPixelValue(int value, @NotNull Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

}
