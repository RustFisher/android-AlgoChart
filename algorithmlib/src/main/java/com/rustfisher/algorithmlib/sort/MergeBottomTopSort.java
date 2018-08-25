package com.rustfisher.algorithmlib.sort;


import com.rustfisher.algorithmlib.AlgoStepSlice;
import com.rustfisher.algorithmlib.ContentInfo;
import com.rustfisher.algorithmlib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自底向上归并排序
 */
public class MergeBottomTopSort extends BaseMergeSort {

    public static List<AlgoStepSlice> bottomTopMergeSort(int a[]) {
        List<AlgoStepSlice> stepEntities = new ArrayList<>();
        btMerge(a, stepEntities);
        return stepEntities;
    }

    private static void btMerge(int a[], List<AlgoStepSlice> algoStepSliceList) {
        int N = a.length;
        // sz 是子数组大小，会翻倍增加
        for (int sz = 1; sz < N; sz = sz + sz) {// low 是子数组的索引
            for (int low = 0; low < N - sz; low += sz + sz) {
                markDots(a, algoStepSliceList, low, low + sz - 1, Math.min(low + sz + sz - 1, N - 1));
                merge(a, low, low + sz - 1, Math.min(low + sz + sz - 1, N - 1));
                markDots(a, algoStepSliceList, low, low + sz - 1, Math.min(low + sz + sz - 1, N - 1));
            }
        }
    }

    @Override
    public Map<ContentInfo, ContentInfo> getDescMap() {
        Map<ContentInfo, ContentInfo> map = new HashMap<>();
        map.put(new ContentInfo(R.string.title_java), new ContentInfo(
                "    public static void mergeSortBU(int a[]) {\n" +
                        "        int N = a.length;\n" +
                        "        for (int sz = 1; sz < N; sz = sz + sz) {\n" +
                        "            for (int low = 0; low < N - sz; low += sz + sz) {\n" +
                        "                merge(a, low, low + sz - 1, \n" +
                        "                    Math.min(low + sz + sz - 1, N - 1));\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }"
        ));
        return map;
    }
}
