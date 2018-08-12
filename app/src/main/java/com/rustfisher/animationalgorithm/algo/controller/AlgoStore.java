package com.rustfisher.animationalgorithm.algo.controller;

import com.rustfisher.animationalgorithm.R;

/**
 * 算法种类
 * 这里是目录列表的定义
 */
public class AlgoStore {
    public static final String K_NUM_ARR = "key_number_array";

    /**
     * 数字数组类型
     */
    public enum NUM_ARR {
        SELECT_SORT(1, R.string.selection_sort),
        INSERTION_SORT(2, R.string.insertion_sort),
        SHELL_SORT(3, R.string.shell_sort),
        QUICK_SORT(4, R.string.quick_sort),
        MERGE_SORT_TOP_BOTTOM(5, R.string.top_bottom_merge_sort),
        MERGE_SORT_BOTTOM_TOP(6, R.string.bottom_top_merge_sort),
        LC_26(7, R.string.lc_26_title),;

        int code;
        int strRes;

        NUM_ARR(int code, int strRes) {
            this.code = code;
            this.strRes = strRes;
        }

        public int getCode() {
            return code;
        }

        public int getStrRes() {
            return strRes;
        }

        public static NUM_ARR getNumberArrayType(int code) {
            switch (code) {
                case 1:
                    return NUM_ARR.SELECT_SORT;
                case 2:
                    return NUM_ARR.INSERTION_SORT;
                case 3:
                    return NUM_ARR.SHELL_SORT;
                case 4:
                    return NUM_ARR.QUICK_SORT;
                case 5:
                    return NUM_ARR.MERGE_SORT_TOP_BOTTOM;
                case 6:
                    return NUM_ARR.MERGE_SORT_BOTTOM_TOP;
                case 7:
                    return LC_26;
            }
            return NUM_ARR.SELECT_SORT;
        }
    }

}
