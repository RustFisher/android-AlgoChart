package com.rustfisher.algorithmlib.sort;


import com.rustfisher.algorithmlib.BaseAlgo;

/**
 * 自顶向下归并排序
 */
public class BaseMergeSort extends BaseAlgo {

    /**
     * 原地归并方法
     */
    protected static void merge(int a[], int low, int mid, int high) {
        int i = low;// left index
        int j = mid + 1;// right index
        int temp[] = new int[high + 1];
        System.arraycopy(a, low, temp, low, high - low + 1);
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = temp[j++];
            } else if (j > high) {
                a[k] = temp[i++];
            } else if (temp[i] < temp[j]) {
                a[k] = temp[i++];
            } else {
                a[k] = temp[j++];
            }
        }
    }

}
