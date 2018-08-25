package com.rustfisher.animationalgorithm.utils;

import android.content.Context;

import com.rustfisher.animationalgorithm.view.DotBarChart;

/**
 * 应用内的设置
 */
public class AppSettings {
    private static final String SETTINGS_SP = "algo_chart_app_settings_sp";

    private static final String K_PLAY_SPEED_PERCENT = "key_play_speed_percent";
    private static final int PLAY_SPEED_DELAY_MM_MIN = 16;
    private static final int PLAY_SPEED_DELAY_MM_MAX = 1000; // 播放时2帧之间的时间间隔

    private static final String K_NUMBER_ARRAY_SIZE_PERCENT = "key_number_array_size_percent";
    private static final int NUMBER_ARRAY_SIZE_MAX = 200;
    private static final int NUMBER_ARRAY_SIZE_MIN = 15;
    public static final int DEF_ARR_SIZE = 15;

    private static final String K_CHART_TYPE = "key_chart_type";

    private static final String K_CHART_SIZE_TYPE = "key_chart_size_type"; // 大小
    public static final int CHART_SIZE_NORMAL = 0;
    public static final int CHART_SIZE_MINI = 1;

    public static void savePlaySpeedPercent(Context context, int percent) {
        context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE).edit()
                .putInt(K_PLAY_SPEED_PERCENT, percent).apply();
    }

    public static int getPlaySpeedPercent(Context context) {
        return context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE).getInt(K_PLAY_SPEED_PERCENT, 10);
    }

    public static int getPlayDelayMM(Context context) {
        float percent = getPlaySpeedPercent(context);
        return (int) (PLAY_SPEED_DELAY_MM_MAX - (percent / 100f) * (PLAY_SPEED_DELAY_MM_MAX - PLAY_SPEED_DELAY_MM_MIN));
    }

    public static void saveNumberArraySizePercent(Context context, int percent) {
        context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE).edit()
                .putInt(K_NUMBER_ARRAY_SIZE_PERCENT, percent).apply();
    }

    public static int getNumberArraySizePercent(Context context) {
        return context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE)
                .getInt(K_NUMBER_ARRAY_SIZE_PERCENT, DEF_ARR_SIZE);
    }

    public static int getNumberArraySize(Context context) {
        float percent = getNumberArraySizePercent(context);
        return (int) (NUMBER_ARRAY_SIZE_MIN + percent / 100f * (NUMBER_ARRAY_SIZE_MAX - NUMBER_ARRAY_SIZE_MIN));
    }

    public static void saveChartType(Context context, DotBarChart.TYPE type) {
        context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE).edit()
                .putInt(K_CHART_TYPE, type.getCode()).apply();
    }

    public static DotBarChart.TYPE getChartType(Context context) {
        int code = context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE)
                .getInt(K_CHART_TYPE, DotBarChart.TYPE.BAR.getCode());
        return DotBarChart.TYPE.getTypeByCode(code);
    }

    public static void saveChartSizeType(Context context, int sizeType) {
        context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE).edit()
                .putInt(K_CHART_SIZE_TYPE, sizeType).apply();
    }

    public static int getChartSizeType(Context context) {
        return context.getSharedPreferences(SETTINGS_SP, Context.MODE_PRIVATE)
                .getInt(K_CHART_SIZE_TYPE, CHART_SIZE_NORMAL);
    }

}
