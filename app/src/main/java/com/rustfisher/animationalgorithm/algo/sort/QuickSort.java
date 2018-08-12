package com.rustfisher.animationalgorithm.algo.sort;


import android.graphics.Color;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.algo.AlgoStepSlice;
import com.rustfisher.animationalgorithm.algo.BaseAlgo;
import com.rustfisher.animationalgorithm.algo.ContentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickSort extends BaseAlgo {

    public static List<AlgoStepSlice> quickSort(int[] a) {
        List<AlgoStepSlice> stepEntities = new ArrayList<>();
        quickSort(a, 0, a.length - 1, stepEntities);
        return stepEntities;
    }

    private static void quickSort(int[] a, int lo, int hi, List<AlgoStepSlice> stepEntities) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        int referValue = a[lo];
        while (i <= gt) {
            if (a[i] < referValue) {
                exchange(a, lt++, i++, lo, hi, stepEntities);
            } else if (a[i] > referValue) {
                exchange(a, i, gt--, lo, hi, stepEntities);
            } else {
                i++;
            }
        }
        quickSort(a, lo, lt - 1, stepEntities);
        quickSort(a, gt + 1, hi, stepEntities);
    }

    private static void exchange(int[] a, int i, int j, int low, int high, List<AlgoStepSlice> stepEntities) {
        markDotsAndBound(a, i, j, low, high, stepEntities);
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        markDotsAndBound(a, j, i, low, high, stepEntities);
    }

    private static void markDotsAndBound(int[] a, int i, int j, int low, int high, List<AlgoStepSlice> stepEntities) {
        AlgoStepSlice algoStepSlice = new AlgoStepSlice(a);
        algoStepSlice.addMarkData(i, Color.YELLOW);
        algoStepSlice.addMarkData(j, Color.GREEN);
        algoStepSlice.addMarkData(low, Color.RED);
        algoStepSlice.addMarkData(high, Color.RED);
        stepEntities.add(algoStepSlice);
    }

    @Override
    public Map<ContentInfo, ContentInfo> getDescMap() {
        Map<ContentInfo, ContentInfo> map = new HashMap<>();
        map.put(new ContentInfo(R.string.title_java),
                new ContentInfo(
                        "    public static void quickSort(int[] a) {\n" +
                                "        quickSort(a, 0, a.length - 1);\n" +
                                "    }\n" +
                                "\n" +
                                "    private static void quickSort(int[] a, int lo, int hi) {\n" +
                                "        if (hi <= lo) return;\n" +
                                "        int lt = lo, i = lo + 1, gt = hi;\n" +
                                "        int referValue = a[lo];\n" +
                                "        while (i <= gt) {\n" +
                                "            if (a[i] < referValue) {\n" +
                                "                exch(a, lt++, i++);\n" +
                                "            } else if (a[i] > referValue) {\n" +
                                "                exch(a, i, gt--);\n" +
                                "            } else {\n" +
                                "                i++;\n" +
                                "            }\n" +
                                "        }\n" +
                                "        quickSort(a, lo, lt - 1);\n" +
                                "        quickSort(a, gt + 1, hi);\n" +
                                "    }\n"));
        map.put(new ContentInfo(R.string.note), new ContentInfo(R.string.quick_sort));
        return map;
    }
}
