package com.rustfisher.animationalgorithm.algo.controller;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.rustfisher.algorithmlib.AlgoStepSlice;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法过程播放控制器
 */
public class AlgoPlayer {
    private static final String TAG = "rustApp";

    public enum STATE {
        NONE, PLAYING, PAUSE
    }

    public interface AListener {
        void onStateChanged(STATE state);
    }

    private STATE mState = STATE.NONE;
    private AListener mListener;
    private CalThread mCalThread;
    private List<AlgoChart> mAlgoChartList = new ArrayList<>();

    private int mCurIndex = 0;
    private int mPlaySleepTimeMM = 250;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private List<AlgoStepSlice> mDataList;

    public void setDataList(List<AlgoStepSlice> dataList) {
        this.mDataList = dataList;
    }

    public void togglePlay() {
        switch (mState) {
            case NONE:
                mState = STATE.PLAYING;
                restartCalThread();
                break;
            case PLAYING:
                pause();
                break;
            case PAUSE:
                mState = STATE.PLAYING;
                notifyState();
                break;
        }
    }

    public void setPlaySleepTimeMM(int mm) {
        this.mPlaySleepTimeMM = mm;
        if (null != mCalThread && mCalThread.isAlive()) {
            mCalThread.setSleepTime(mm);
        }
    }

    public void pressPrevious() {
        pause();
        playPreStep();
    }

    public void pressNext() {
        pause();
        mCurIndex++;
        if (mCurIndex >= mDataList.size()) {
            mCurIndex = mDataList.size() - 1; // 点击下一步不能跳出数据范围
        }
        outputData();
    }

    private void pause() {
        if (STATE.PLAYING.equals(mState)) {
            mState = STATE.PAUSE;
            notifyState();
        }
    }

    public boolean pausing() {
        return STATE.PAUSE.equals(mState);
    }

    public void exit() {
        onPlayFinish();
    }

    private void onPlayFinish() {
        mState = STATE.NONE;
        finishCalThread();
        mCurIndex = 0;
        notifyState();
    }

    public void addAlgoChart(AlgoChart algoChart) {
        mAlgoChartList.add(algoChart);
    }

    public void setListener(AListener listener) {
        this.mListener = listener;
    }

    private void restartCalThread() {
        finishCalThread();
        mCalThread = new CalThread();
        mCalThread.setSleepTime(mPlaySleepTimeMM);
        mCalThread.start();
    }

    private void finishCalThread() {
        if (null != mCalThread) {
            mCalThread.mmLooping = false;
            mCalThread.interrupt();
            mCalThread = null;
        }
    }

    private class CalThread extends Thread {
        boolean mmLooping;
        int mmSleepTime = 250;

        CalThread() {
            mmLooping = true;
        }

        void setSleepTime(int time) {
            this.mmSleepTime = time;
        }

        @Override
        public void run() {
            mState = STATE.PLAYING;
            Log.d(TAG, "播放线程启动: " + mState);
            notifyState();
            while (mmLooping && !isInterrupted()) {
                if (pausing()) {
                    Log.d(TAG, "已暂停");
                    try {
                        Thread.sleep(mmSleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    continue;
                }
                final int dataSize = mDataList.size();
                mCurIndex++;
                outputData();
                if (mCurIndex >= dataSize) {
                    break;
                }
                try {
                    Thread.sleep(mmSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            onPlayFinish();
        }
    }

    private void playPreStep() {
        mCurIndex--;
        if (mCurIndex < 0) {
            mCurIndex = 0;
        }
        outputData();
    }

    private void outputData() {
        if (!mAlgoChartList.isEmpty() && mCurIndex < mDataList.size()) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    for (AlgoChart algoChart : mAlgoChartList) {
                        if (null != algoChart) {
                            algoChart.showData(mDataList.get(mCurIndex));
                        }
                    }
                }
            });
        }
    }

    private void notifyState() {
        if (null != mListener) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mListener.onStateChanged(mState);
                }
            });
        }
    }
}
