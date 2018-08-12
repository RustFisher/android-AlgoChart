package com.rustfisher.animationalgorithm.algo.sort;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.algo.AlgoStepSlice;
import com.rustfisher.animationalgorithm.algo.BaseAlgo;
import com.rustfisher.animationalgorithm.algo.ContentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插入排序
 */
public class InsertionSort extends BaseAlgo {

    public static List<AlgoStepSlice> insertionSort(int a[]) {
        List<AlgoStepSlice> stepEntities = new ArrayList<>();
        int N = a.length;
        stepEntities.add(new AlgoStepSlice(a));
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && (a[j] < a[j - 1]); j--) {
                markDots(a, stepEntities, i, j);
                exch(a, j, j - 1);
                markDots(a, stepEntities, i, j - 1);
            }
        }
        markDots(a, stepEntities, N - 1);
        return stepEntities;
    }

    @Override
    public Map<ContentInfo, ContentInfo> getDescMap() {
        Map<ContentInfo, ContentInfo> map = new HashMap<>();
        map.put(new ContentInfo(R.string.title_java),
                new ContentInfo(
                        "    public static void insertionSort(int a[]) {\n" +
                                "        int N = a.length;\n" +
                                "        for (int i = 1; i < N; i++) {\n" +
                                "            for (int j = i; j > 0 && (a[j] < a[j - 1]); j--) {\n" +
                                "                exch(a, j, j - 1);\n" +
                                "            }\n" +
                                "        }\n" +
                                "    }"));
        map.put(new ContentInfo(R.string.note), new ContentInfo(R.string.insertion_sort));
        return map;
    }
}
