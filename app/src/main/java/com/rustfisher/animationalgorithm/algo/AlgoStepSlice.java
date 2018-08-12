package com.rustfisher.animationalgorithm.algo;

import android.util.SparseIntArray;

/**
 * 算法步骤
 */
public class AlgoStepSlice {

    // 答案类型
    public enum AnswerType {
        INT, STRING
    }

    public int[] dataArray;
    public SparseIntArray indexColorMap = new SparseIntArray();

    public boolean hasAnswer = false;
    private AnswerType answerType;
    private int intAns;

    public void setAnswerType(AnswerType type) {
        answerType = type;
        hasAnswer = true;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public AlgoStepSlice(int[] data) {
        dataArray = data.clone();
    }

    public AlgoStepSlice(int[] data, int ans) {
        dataArray = data.clone();
        intAns = ans;
        setAnswerType(AnswerType.INT);
    }

    public static AlgoStepSlice genIntAnsSlice(int[] data, int ans) {
        return new AlgoStepSlice(data, ans);
    }

    public void addMarkData(int index, int colorInt) {
        indexColorMap.put(index, colorInt);
    }
}
