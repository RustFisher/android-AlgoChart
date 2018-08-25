package com.rustfisher.algorithmlib;


import android.graphics.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 算法基础类
 */
public class BaseAlgo {

    public static int[] colors = new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLACK};

    public Map<ContentInfo, ContentInfo> baseDescMap = new HashMap<>();

    public static void exch(int a[], int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * v < w 返回 true
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; //v < w 返回 -1
    }

    /**
     * @return 按顺序组装的说明文件
     */
    public Map<ContentInfo, ContentInfo> getDescMap() {
        return null;
    }

    protected static void markDots(int[] a, List<AlgoStepSlice> stepEntities, int... indices) {
        AlgoStepSlice algoStepSlice = new AlgoStepSlice(a);
        if (null != indices) {
            for (int i = 0; i < indices.length; i++) {
                algoStepSlice.addMarkData(indices[i], colors[i % colors.length]);
            }
        }
        stepEntities.add(algoStepSlice);
    }
}
