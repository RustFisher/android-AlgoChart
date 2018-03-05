package com.rustfisher.animationalgorithm.utils;

import android.content.res.Resources;

/**
 * Author : xks
 * Data : 2018/3/6 0006
 * Des :
 */

public class DpUtils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
