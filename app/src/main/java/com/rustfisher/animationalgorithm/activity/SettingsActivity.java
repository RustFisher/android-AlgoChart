package com.rustfisher.animationalgorithm.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.fragment.SettingsFragment;

/**
 * 设置页
 */
public class SettingsActivity extends AppCompatActivity {

    Toolbar mToolbar;
    SettingsFragment mSettingsFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_toolbar_frame);

        initUI();
        initFragmentContent();
    }

    private void initFragmentContent() {
        mSettingsFragment = new SettingsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, mSettingsFragment).commit();
    }

    private void initUI() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setTitle(R.string.settings);
    }
}
