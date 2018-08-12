package com.rustfisher.animationalgorithm.algo.controller;

import com.rustfisher.animationalgorithm.algo.AlgoStepSlice;

public interface AlgoChart {

    /**
     * 一般在主线程使用
     *
     * @param data 要显示的数据
     */
    void showData(AlgoStepSlice data);
}
