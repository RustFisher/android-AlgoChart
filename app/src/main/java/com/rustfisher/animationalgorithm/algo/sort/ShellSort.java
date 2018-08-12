package com.rustfisher.animationalgorithm.algo.sort;


import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.algo.AlgoStepSlice;
import com.rustfisher.animationalgorithm.algo.BaseAlgo;
import com.rustfisher.animationalgorithm.algo.ContentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShellSort extends BaseAlgo {

    public static List<AlgoStepSlice> shellSort(int a[]) {
        List<AlgoStepSlice> stepEntities = new ArrayList<>();
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;// 找到最大的h
        }
        while (h >= 1) {
            for (int i = h; i < N; i += h) { // 以h为查询间隔
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    markDots(a, stepEntities, j, j - h);
                    exch(a, j, j - h);// 插入排序
                    markDots(a, stepEntities, j, j - h);
                }
                markDots(a, stepEntities, i);
            }
            h = h / 3;// 当h=1时，变成了插入排序
        }
        return stepEntities;
    }

    @Override
    public Map<ContentInfo, ContentInfo> getDescMap() {
        Map<ContentInfo, ContentInfo> map = new HashMap<>();
        map.put(new ContentInfo(R.string.title_java),
                new ContentInfo(
                        "    public static void shellSort(int a[]) {\n" +
                                "        int N = a.length;\n" +
                                "        int h = 1;\n" +
                                "        while (h < N / 3) {\n" +
                                "            h = 3 * h + 1;\n" +
                                "        }\n" +
                                "        while (h >= 1) {\n" +
                                "            for (int i = h; i < N; i+=h) {\n" +
                                "                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {\n" +
                                "                    exch(a, j, j - h);\n" +
                                "                }\n" +
                                "            }\n" +
                                "            h = h / 3;\n" +
                                "        }\n" +
                                "    }"));
        return map;
    }

}
