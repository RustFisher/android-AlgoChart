package com.rustfisher.animationalgorithm.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.rustfisher.animationalgorithm.BuildConfig;
import com.rustfisher.animationalgorithm.R;

/**
 * 关于
 */
public class AboutActivity extends AppCompatActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_about);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back_white);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle(R.string.about);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.github_url_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(getString(R.string.github_url));
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        TextView versionTv = findViewById(R.id.version_tv);
        versionTv.setText(BuildConfig.VERSION_NAME);
    }
}
