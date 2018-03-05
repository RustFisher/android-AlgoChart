package com.rustfisher.animationalgorithm.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.activity.MainActivity;
import com.rustfisher.animationalgorithm.utils.DpUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/8.
 */
public class AlgoListAdapter extends BaseAdapter {


    private Context context;
    private ViewHolder viewHolder;

    private ArrayList<String> data = new ArrayList<>();
    private int height;


    public AlgoListAdapter(Context context) {
        this.context = context;

    }

    public void setData(ArrayList<String> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<String> list) {
        this.data.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.algo_list_item, null);
            viewHolder.name = convertView.findViewById(R.id.algo_name);
            viewHolder.icon = convertView.findViewById(R.id.algo_icon);
            viewHolder.relativeLayout = convertView.findViewById(R.id.algo_relativelayout);
            viewHolder.cardView = convertView.findViewById(R.id.algo_cardview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String name = data.get(i);
        viewHolder.name.setText(name);
        //设置字体
//        viewHolder.name.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/yuanti.ttf"));
        viewHolder.relativeLayout.setBackgroundResource(i == 0 || i == 3 || i == 4 ? R.drawable.algo_list_bg1 : R.drawable.algo_list_bg2);
        switch (i) {
            case 0:
                viewHolder.icon.setImageResource(R.mipmap.algo4_4);
                break;
            case 1:
                viewHolder.icon.setImageResource(R.mipmap.algo2);
                break;
            case 2:
                viewHolder.icon.setImageResource(R.mipmap.algo3);
                break;
            case 3:
                viewHolder.icon.setImageResource(R.mipmap.algo1_1);
                break;
            case 4:
                viewHolder.icon.setImageResource(R.mipmap.algo6_6);
                break;
            case 5:
                viewHolder.icon.setImageResource(R.mipmap.algo5);
                break;
        }
        //动态设置item 高度 铺满屏幕 1080 * 1920
        int h = (height - DpUtils.dpToPx(113)) / 3;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams( FrameLayout.LayoutParams.MATCH_PARENT, h);
        layoutParams.setMargins(15,15,15,15);
        viewHolder.cardView.setLayoutParams(layoutParams);
        return convertView;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private static class ViewHolder {
        private TextView name;//名称
        private ImageView icon;//名称
        private RelativeLayout relativeLayout;//背景
        private CardView cardView;//布局
    }
}
