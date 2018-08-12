package com.rustfisher.animationalgorithm.algo.controller;

import com.rustfisher.animationalgorithm.utils.AppSettings;

import java.util.Random;

/**
 * 管理数组
 */
public class ArraySource {

    public static int[] genIntArray() {
        return genIntArray(AppSettings.DEF_ARR_SIZE);
    }

    public static int[] genIntArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        randomMix(arr);
        return arr;
    }

    private static void randomMix(int[] arr) {
        Random random = new Random();
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int targetIndex = random.nextInt(len);
            int tmp = arr[i];
            arr[i] = arr[targetIndex];
            arr[targetIndex] = tmp;
        }
    }

}
