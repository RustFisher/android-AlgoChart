package com.rustfisher.animationalgorithm.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.algo.controller.AlgoChart;
import com.rustfisher.animationalgorithm.algo.controller.AlgoPlayer;
import com.rustfisher.animationalgorithm.algo.AlgoStepSlice;
import com.rustfisher.animationalgorithm.algo.controller.AlgoStore;
import com.rustfisher.animationalgorithm.algo.controller.ArraySource;
import com.rustfisher.animationalgorithm.algo.ContentInfo;
import com.rustfisher.animationalgorithm.algo.leetcode.array.LC26RemoveDuplicatesFromSortedArray;
import com.rustfisher.animationalgorithm.view.DotBarChart;
import com.rustfisher.animationalgorithm.algo.BaseAlgo;
import com.rustfisher.animationalgorithm.algo.sort.InsertionSort;
import com.rustfisher.animationalgorithm.algo.sort.MergeBottomTopSort;
import com.rustfisher.animationalgorithm.algo.sort.MergeTopBottomSort;
import com.rustfisher.animationalgorithm.algo.sort.QuickSort;
import com.rustfisher.animationalgorithm.algo.sort.SelectionSort;
import com.rustfisher.animationalgorithm.algo.sort.ShellSort;
import com.rustfisher.animationalgorithm.utils.AppSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SortFragment extends Fragment {
    private static final String TAG = "algoApp";

    private View mChartField;
    private View mMiniChartField;
    private DotBarChart mBigChart;
    private DotBarChart mMiniChart;
    private AlgoPlayer mAlgoPlayer;

    ImageView mShowBigChartIv;
    ImageView mHideBigChartIv;
    private ImageView mPlayIv;
    private int[] mNumberArray = ArraySource.genIntArray();
    private RadioButton mTypeDotRBtn;
    private RadioButton mTypeBarRBtn;
    SeekBar mSpeedPercentSb;
    private AlgoStore.NUM_ARR mAlgoType = AlgoStore.NUM_ARR.SELECT_SORT;

    private int mWindowWid = 1440; // 屏幕宽度

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int numberArraySize = AppSettings.getNumberArraySize(getActivity());
        mNumberArray = ArraySource.genIntArray(numberArraySize);
        Bundle bundle = getArguments();
        if (null != bundle) {
            mAlgoType = AlgoStore.NUM_ARR.getNumberArrayType(bundle.getInt(AlgoStore.K_NUM_ARR, -1));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_algo_chart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        List<AlgoStepSlice> algoStepEntities = null;
        BaseAlgo sort = new BaseAlgo();
        switch (mAlgoType) {
            case SELECT_SORT:
                algoStepEntities = SelectionSort.selectSort(mNumberArray);
                Log.d(TAG, "algoStepEntities.size: " + algoStepEntities.size());
                sort = new SelectionSort();
                break;
            case INSERTION_SORT:
                algoStepEntities = InsertionSort.insertionSort(mNumberArray);
                Log.d(TAG, "algoStepEntities.size: " + algoStepEntities.size());
                sort = new InsertionSort();
                break;
            case SHELL_SORT:
                algoStepEntities = ShellSort.shellSort(mNumberArray);
                Log.d(TAG, "algoStepEntities.size: " + algoStepEntities.size());
                sort = new ShellSort();
                break;
            case QUICK_SORT:
                algoStepEntities = QuickSort.quickSort(mNumberArray);
                Log.d(TAG, "algoStepEntities.size: " + algoStepEntities.size());
                sort = new QuickSort();
                break;
            case MERGE_SORT_TOP_BOTTOM:
                algoStepEntities = MergeTopBottomSort.topBottomMergeSort(mNumberArray);
                Log.d(TAG, "algoStepEntities.size: " + algoStepEntities.size());
                sort = new MergeTopBottomSort();
                break;
            case MERGE_SORT_BOTTOM_TOP:
                algoStepEntities = MergeBottomTopSort.bottomTopMergeSort(mNumberArray);
                Log.d(TAG, "algoStepEntities.size: " + algoStepEntities.size());
                sort = new MergeBottomTopSort();
                break;
            case LC_26:
                int[] lc26Data = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 9, 3, 4, 2, 5, 6, 7};
                mBigChart.setShowDataMax(11);
                mMiniChart.setShowDataMax(11);
                mBigChart.setData(lc26Data, null);
                mMiniChart.setData(lc26Data, null);
                algoStepEntities = LC26RemoveDuplicatesFromSortedArray.removeDuplicates(lc26Data);
                sort = new LC26RemoveDuplicatesFromSortedArray();
                break;
        }
        mAlgoPlayer = new AlgoPlayer();
        mAlgoPlayer.setPlaySleepTimeMM(AppSettings.getPlayDelayMM(getActivity()));
        mAlgoPlayer.setDataList(algoStepEntities);
        mAlgoPlayer.addAlgoChart(mAlgoChart);
        mAlgoPlayer.setListener(aListener);

        initTextPager(view, sort);
    }

    private void initUI(final View root) {
        mMiniChart = root.findViewById(R.id.mini_chart);
        mShowBigChartIv = root.findViewById(R.id.show_big_chart_iv);
        mHideBigChartIv = root.findViewById(R.id.hide_chart_iv);
        mChartField = root.findViewById(R.id.algo_chart_field);
        mTypeBarRBtn = root.findViewById(R.id.bar_style);
        mTypeDotRBtn = root.findViewById(R.id.dot_style);
        mPlayIv = root.findViewById(R.id.play_iv);
        mMiniChartField = root.findViewById(R.id.mini_chart_field);
        mBigChart = root.findViewById(R.id.dbc1);
        mSpeedPercentSb = root.findViewById(R.id.speed_sb);

        mPlayIv.setOnClickListener(mOnClickListener);
        root.findViewById(R.id.next_iv).setOnClickListener(mOnClickListener);
        root.findViewById(R.id.pre_iv).setOnClickListener(mOnClickListener);
        mBigChart.setData(mNumberArray, null);
        mBigChart.setShowType(AppSettings.getChartType(getActivity()));
        mMiniChart.setData(mNumberArray, null);
        mMiniChart.setShowType(AppSettings.getChartType(getActivity()));

        updateTypeUI();
        RadioGroup styleGroup = root.findViewById(R.id.style_group);
        styleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bar_style:
                        mBigChart.setShowType(DotBarChart.TYPE.BAR);
                        mMiniChart.setShowType(DotBarChart.TYPE.BAR);
                        break;
                    case R.id.dot_style:
                        mBigChart.setShowType(DotBarChart.TYPE.DOT);
                        mMiniChart.setShowType(DotBarChart.TYPE.DOT);
                        break;
                }
            }
        });
        mBigChart.setShowDataMax(mNumberArray.length * 1.1f);
        mMiniChart.setShowDataMax(mNumberArray.length * 1.1f);

        mSpeedPercentSb.setProgress(AppSettings.getPlaySpeedPercent(getActivity()));
        mSpeedPercentSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                AppSettings.savePlaySpeedPercent(getActivity(), seekBar.getProgress());
                mAlgoPlayer.setPlaySleepTimeMM(AppSettings.getPlayDelayMM(getActivity()));
            }
        });
        mShowBigChartIv.setOnClickListener(mOnClickListener);
        mHideBigChartIv.setOnClickListener(mOnClickListener);
        mHideBigChartIv.post(new Runnable() {
            @Override
            public void run() {
                Point rootPoint = new Point();
                getActivity().getWindow().getWindowManager().getDefaultDisplay().getSize(rootPoint);
                mWindowWid = rootPoint.y;
                Log.d(TAG, "window wid: " + mWindowWid);
            }
        });
    }

    private void initTextPager(View view, BaseAlgo sort) {
        List<Fragment> childFrags = new ArrayList<>();

        // 装载要显示的内容
        Map<ContentInfo, ContentInfo> infoMap = sort.getDescMap();
        if (null != infoMap && !infoMap.isEmpty()) {
            List<ContentInfo> kInfoList = new ArrayList<>(infoMap.keySet());
            ContentInfo.sortByNumber(kInfoList);
            for (ContentInfo kInfo : kInfoList) {
                ContentInfo contentInfo = infoMap.get(kInfo);
                if (contentInfo.hasId) {
                    genContentFrag(childFrags, getString(contentInfo.textId));
                } else if (contentInfo.hasString) {
                    genContentFrag(childFrags, contentInfo.contentString);
                }
            }
        }

        ViewPager viewPager = view.findViewById(R.id.vp);
        ContentFragmentPagerAdapter pagerAdapter = new ContentFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.setFragmentList(childFrags);
        viewPager.setAdapter(pagerAdapter);
    }

    private void genContentFrag(List<Fragment> childFrags, String desc) {
        if (!TextUtils.isEmpty(desc)) {
            TextFragment javaFrag = new TextFragment();
            javaFrag.setText(desc);
            childFrags.add(javaFrag);
        }
    }

    class ContentFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mmFragmentList;

        ContentFragmentPagerAdapter(FragmentManager manager) {
            super(manager);
            mmFragmentList = new ArrayList<>();
        }

        void setFragmentList(List<Fragment> list) {
            this.mmFragmentList = new ArrayList<>(list);
        }

        @Override
        public Fragment getItem(int position) {
            return mmFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mmFragmentList.size();
        }
    }

    private AlgoChart mAlgoChart = new AlgoChart() {
        @Override
        public void showData(AlgoStepSlice entity) {
            mBigChart.setData(entity.dataArray, entity.indexColorMap);
            mMiniChart.setData(entity.dataArray, entity.indexColorMap);
        }
    };

    private AlgoPlayer.AListener aListener = new AlgoPlayer.AListener() {
        @Override
        public void onStateChanged(AlgoPlayer.STATE state) {
            if (isVisible()) {
                switch (state) {
                    case NONE:
                    case PAUSE:
                        mPlayIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_media_play));
                        break;
                    case PLAYING:
                        mPlayIv.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause1));
                        break;
                }
            }
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.play_iv:
                    mAlgoPlayer.togglePlay();
                    break;
                case R.id.pre_iv:
                    mAlgoPlayer.pressPrevious();
                    break;
                case R.id.next_iv:
                    mAlgoPlayer.pressNext();
                    break;
                case R.id.show_big_chart_iv:
                    changeBigMiniChart(true);
                    break;
                case R.id.hide_chart_iv:
                    changeBigMiniChart(false);
                    break;
            }
        }
    };

    private void updateTypeUI() {
        switch (mBigChart.getShowType()) {
            case DOT:
                mTypeDotRBtn.setChecked(true);
                break;
            case BAR:
                mTypeBarRBtn.setChecked(true);
                break;
        }
    }

    private void changeBigMiniChart(boolean showBig) {
        if (showBig) {
            mChartField.setVisibility(View.VISIBLE);
            mMiniChartField.setVisibility(View.GONE);
        } else {
            mChartField.setVisibility(View.GONE);
            mMiniChartField.setVisibility(View.VISIBLE);
        }
    }
}
