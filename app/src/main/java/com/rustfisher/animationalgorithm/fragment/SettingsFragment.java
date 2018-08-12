package com.rustfisher.animationalgorithm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.view.DotBarChart;
import com.rustfisher.animationalgorithm.utils.AppSettings;

/**
 * 设置页Fragment
 */
public class SettingsFragment extends Fragment {

    SeekBar mSpeedPercentSb;
    SeekBar mNumberArraySizeSb;
    RadioGroup mChartTypeRg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSpeedPercentSb = view.findViewById(R.id.speed_sb);
        mNumberArraySizeSb = view.findViewById(R.id.number_array_size_sb);
        mChartTypeRg = view.findViewById(R.id.style_group);

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
            }
        });
        mNumberArraySizeSb.setProgress(AppSettings.getNumberArraySizePercent(getActivity()));
        mNumberArraySizeSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                AppSettings.saveNumberArraySizePercent(getActivity(), seekBar.getProgress());
            }
        });
        RadioButton dotRb = view.findViewById(R.id.dot_style_rb);
        RadioButton barRb = view.findViewById(R.id.bar_style_rb);
        switch (AppSettings.getChartType(getActivity())) {
            case DOT:
                dotRb.setChecked(true);
                break;
            case BAR:
                barRb.setChecked(true);
                break;
        }
        mChartTypeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.dot_style_rb:
                        AppSettings.saveChartType(getActivity(), DotBarChart.TYPE.DOT);
                        break;
                    case R.id.bar_style_rb:
                        AppSettings.saveChartType(getActivity(), DotBarChart.TYPE.BAR);
                        break;
                }
            }
        });
    }
}
