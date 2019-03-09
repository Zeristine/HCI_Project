package day01.huy.hci_project.ultis;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;

import org.jetbrains.annotations.NotNull;

import day01.huy.hci_project.R;

public class ColorGradient {

    public static GradientDrawable getRedGradient(@NotNull Context context){
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        context.getResources().getColor(R.color.red100),
                        context.getResources().getColor(R.color.red200),
                        context.getResources().getColor(R.color.red300),
                        context.getResources().getColor(R.color.red400)
                });
        gradientDrawable.setCornerRadius(10);
        return gradientDrawable;
    }

    public static GradientDrawable getRedGradientDeep(@NotNull Context context){
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        context.getResources().getColor(R.color.red200),
                        context.getResources().getColor(R.color.red400),
                        context.getResources().getColor(R.color.red500),
                        context.getResources().getColor(R.color.red800)
                });
        gradientDrawable.setCornerRadius(10);
        return gradientDrawable;
    }

    public static GradientDrawable getRedGradientDeeper(@NotNull Context context){
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        context.getResources().getColor(R.color.red800),
                        context.getResources().getColor(R.color.redA400),
                        context.getResources().getColor(R.color.redA700)
                });
        gradientDrawable.setCornerRadius(10);
        return gradientDrawable;
    }

    public static GradientDrawable getRedGradientOrange(@NotNull Context context){
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{

                        context.getResources().getColor(R.color.red400),
                        context.getResources().getColor(R.color.orange),
                        context.getResources().getColor(R.color.orange),
                        context.getResources().getColor(R.color.orange),
                        context.getResources().getColor(R.color.red400)
                });
        gradientDrawable.setCornerRadius(10);
        return gradientDrawable;
    }

    public static GradientDrawable getRedGradientBlackGray(@NotNull Context context){
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        context.getResources().getColor(R.color.red300),
                        context.getResources().getColor(R.color.grayDark),
                        context.getResources().getColor(R.color.grayDark),
                        context.getResources().getColor(R.color.grayDark),
                        context.getResources().getColor(R.color.grayDark),
                        context.getResources().getColor(R.color.red300)
                });
        gradientDrawable.setCornerRadius(10);
        return gradientDrawable;
    }

    public static GradientDrawable getRedGradientCircle(@NotNull Context context){
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        context.getResources().getColor(R.color.red300),
                        context.getResources().getColor(R.color.red500),
                        context.getResources().getColor(R.color.red500),
                        context.getResources().getColor(R.color.red300)
                });
        gradientDrawable.setCornerRadius(90);
        return gradientDrawable;
    }
}
