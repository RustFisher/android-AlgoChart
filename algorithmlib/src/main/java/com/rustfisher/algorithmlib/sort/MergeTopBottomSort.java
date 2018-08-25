package com.rustfisher.algorithmlib.sort;


import com.rustfisher.algorithmlib.AlgoStepSlice;
import com.rustfisher.algorithmlib.ContentInfo;
import com.rustfisher.algorithmlib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自顶向下归并排序
 */
public class MergeTopBottomSort extends BaseMergeSort {

    public static List<AlgoStepSlice> topBottomMergeSort(int a[]) {
        List<AlgoStepSlice> stepEntities = new ArrayList<>();
        topBottomMergeSort(a, stepEntities);
        return stepEntities;
    }

    private static void topBottomMergeSort(int a[], List<AlgoStepSlice> stepEntities) {
        iMergeSort(a, 0, a.length - 1, stepEntities);
    }

    private static void iMergeSort(int a[], int low, int high, List<AlgoStepSlice> stepEntities) {
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        markDots(a, stepEntities, low, mid, high);
        iMergeSort(a, low, mid, stepEntities);           // 将左半部分排序  左半部分变成有序数组
        iMergeSort(a, mid + 1, high, stepEntities); // 将右半部分排序  右半部分变成有序数组
        merge(a, low, mid, high);          // 调用原地归并方法
        markDots(a, stepEntities, low, mid, high);
    }

    @Override
    public Map<ContentInfo, ContentInfo> getDescMap() {
        Map<ContentInfo, ContentInfo> map = new HashMap<>();
        map.put(new ContentInfo(R.string.title_java), new ContentInfo(
                "    private static void mergeSort(int a[]) {\n" +
                        "        iMergeSort(a, 0, a.length - 1);\n" +
                        "    }\n" +
                        "    \n" +
                        "    private static void iMergeSort(int a[], int low, int high) {\n" +
                        "        if (high <= low) return;\n" +
                        "        int mid = low + (high - low) / 2;\n" +
                        "        iMergeSort(a, low, mid);         \n" +
                        "        iMergeSort(a, mid + 1, high);    \n" +
                        "        merge(a, low, mid, high);        \n" +
                        "    }\n" +
                        "    \n" +
                        "    private static void merge(int a[], int low, int mid, int high) {\n" +
                        "        int i = low;// 左数组索引\n" +
                        "        int j = mid + 1;// 右数组索引\n" +
                        "        int temp[] = new int[high + 1];\n" +
                        "        for (int k = low; k <= high; k++) {\n" +
                        "            temp[k] = a[k];// 全部复制到辅助数组中\n" +
                        "        }\n" +
                        "        for (int k = low; k <= high; k++) {\n" +
                        "            if (i > mid) {\n" +
                        "                a[k] = temp[j++];\n" +
                        "            } else if (j > high) {\n" +
                        "                a[k] = temp[i++];\n" +
                        "            } else if (temp[i] < temp[j]) {\n" +
                        "                a[k] = temp[i++];\n" +
                        "            } else {\n" +
                        "                a[k] = temp[j++];\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n"
        ));
        return map;
    }
}
