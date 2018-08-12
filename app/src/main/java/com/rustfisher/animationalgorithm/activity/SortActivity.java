package com.rustfisher.animationalgorithm.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.algo.controller.AlgoStore;
import com.rustfisher.animationalgorithm.fragment.SortFragment;

public class SortActivity extends AppCompatActivity {

    Toolbar mToolbar;
    SortFragment mSortFragment;
    private AlgoStore.NUM_ARR mNumAlgoType = AlgoStore.NUM_ARR.SELECT_SORT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_toolbar_frame);
        Intent input = getIntent();
        if (null != input) {
            mNumAlgoType = AlgoStore.NUM_ARR.getNumberArrayType(input.getIntExtra(AlgoStore.K_NUM_ARR, -1));
        }

        mToolbar = findViewById(R.id.toolbar);
        mSortFragment = new SortFragment();
        Bundle sBundle = new Bundle();
        sBundle.putInt(AlgoStore.K_NUM_ARR, mNumAlgoType.getCode());
        mSortFragment.setArguments(sBundle);

        getSupportFragmentManager().beginTransaction().add(R.id.container, mSortFragment).commit();

        String title = getString(mNumAlgoType.getStrRes());
        if (title.length() > 20) {
            TextView wrapTitleTv = findViewById(R.id.wrap_title_tv);
            wrapTitleTv.setVisibility(View.VISIBLE);
            wrapTitleTv.setText(title);
            mToolbar.setTitle("");
        } else {
            mToolbar.setTitle(title);
        }
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.play_page_menu, menu);
        return true;
    }
}
