package com.rustfisher.algorithmlib.sort;


import com.rustfisher.algorithmlib.AlgoStepSlice;
import com.rustfisher.algorithmlib.BaseAlgo;
import com.rustfisher.algorithmlib.ContentInfo;
import com.rustfisher.algorithmlib.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectionSort extends BaseAlgo {

    public static List<AlgoStepSlice> selectSort(int a[]) {
        List<AlgoStepSlice> stepEntities = new ArrayList<>();
        int N = a.length;
        stepEntities.add(new AlgoStepSlice(a));
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            markDots(a, stepEntities, min, i);
            exch(a, i, min);
            markDots(a, stepEntities, min, i);
        }
        return stepEntities;
    }

    @Override
    public Map<ContentInfo, ContentInfo> getDescMap() {
        baseDescMap.put(new ContentInfo(R.string.title_java),
                new ContentInfo(
                        "        public static void selectionSort(int a[]) {\n" +
                                "            int N = a.length;\n" +
                                "            for (int i = 0; i < N; i++) {\n" +
                                "                int min = i;\n" +
                                "                for (int j = i + 1; j < N; j++) {\n" +
                                "                    if (less(a[j], a[min])) min = j;\n" +
                                "                }\n" +
                                "                exch(a, i, min);\n" +
                                "            }\n" +
                                "        }"));
        return baseDescMap;
    }
}
