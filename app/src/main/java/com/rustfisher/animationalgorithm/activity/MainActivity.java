package com.rustfisher.animationalgorithm.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.algo.controller.AlgoStore;
import com.rustfisher.animationalgorithm.view.AlgoReAdapter;
import com.rustfisher.animationalgorithm.view.MainDrawerAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "algoChart";
    private DrawerLayout mDrawerLayout;
    AlgoReAdapter mAlgoReAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initView();
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);//设置主标题
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        initAlgoList();
        initDrawer();
    }

    private void initAlgoList() {
        RecyclerView aReView = findViewById(R.id.main_re_view);
        mAlgoReAdapter = new AlgoReAdapter();
        for (int i = 1; i <= AlgoStore.NUM_ARR.LC_26.getCode(); i++) {
            mAlgoReAdapter.addSortItem(AlgoStore.NUM_ARR.getNumberArrayType(i));
        }
        aReView.setAdapter(mAlgoReAdapter);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        aReView.setLayoutManager(layoutManager);
        mAlgoReAdapter.notifyDataSetChanged();
        mAlgoReAdapter.setOnItemListener(new AlgoReAdapter.OnItemListener() {
            @Override
            public void onItemOnClick(AlgoReAdapter.AlgoEntity algoEntity) {
                if (algoEntity.isNumArray) {
                    Intent intent = new Intent(getApplicationContext(), SortActivity.class);
                    intent.putExtra(AlgoStore.K_NUM_ARR, algoEntity.numArr.getCode());
                    startActivity(intent);
                }
            }
        });
        aReView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 20;
                outRect.left = 10;
                outRect.right = 10;
            }
        });
    }

    private void initDrawer() {
        MainDrawerAdapter mainDrawerAdapter = new MainDrawerAdapter();
        RecyclerView drawerRv = findViewById(R.id.drawer_rv);
        drawerRv.setAdapter(mainDrawerAdapter);
        drawerRv.setLayoutManager(new LinearLayoutManager(this));
        mainDrawerAdapter.setOnItemClickListener(new MainDrawerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(MainDrawerAdapter.DrawerItem item) {
                if (item.isMenuItem()) {
                    MainDrawerAdapter.DrawerItemNormal menuItem = (MainDrawerAdapter.DrawerItemNormal) item;
                    switch (menuItem.code) {
                        case MainDrawerAdapter.ITEM_ABOUT:
                            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                            break;
                        case MainDrawerAdapter.ITEM_SETTINGS:
                            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                            break;
                    }
                }
            }
        });
        drawerRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            Paint mmDividerPaint = new Paint();
            final int bottomOffset = 10;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = bottomOffset;
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                Resources resources = parent.getResources();
                int childCount = parent.getChildCount();
                int left = parent.getPaddingLeft()
                        + resources.getDimensionPixelOffset(R.dimen.main_drawer_item_decoration_on_draw_margin_left);
                int right = parent.getWidth()
                        - parent.getPaddingRight()
                        - resources.getDimensionPixelOffset(R.dimen.main_drawer_item_decoration_on_draw_margin_right);
                mmDividerPaint.setColor(resources.getColor(R.color.mainDrawerItemDivider));
                for (int i = 0; i < childCount - 1; i++) {
                    View view = parent.getChildAt(i);
                    float top = view.getBottom();
                    float bottom = view.getBottom() + bottomOffset / 2;
                    c.drawRect(left, top, right, bottom, mmDividerPaint);
                }
            }
        });
        findViewById(R.id.drawer_header_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "onWindowFocusChanged: hasFocus: " + hasFocus);
    }
}
