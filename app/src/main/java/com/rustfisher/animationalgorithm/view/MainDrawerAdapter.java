package com.rustfisher.animationalgorithm.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rustfisher.animationalgorithm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Dashboard drawer
 * Created by Rust on 2018/8/19.
 */
public class MainDrawerAdapter extends RecyclerView.Adapter<MainDrawerAdapter.DrawerViewHolder> {
    public static final int ITEM_SETTINGS = 1;
    public static final int ITEM_ABOUT = 100;

    private static final int TYPE_DIVIDER = 0;
    private static final int TYPE_NORMAL = 1;

    private List<DrawerItem> dataList;

    public MainDrawerAdapter() {
        dataList = new ArrayList<>(); // 在这里配置菜单列表
        dataList.add(new DrawerItemNormal(ITEM_SETTINGS, R.drawable.ic_setting, R.string.settings));
        dataList.add(new DrawerItemNormal(ITEM_ABOUT, R.drawable.ic_about, R.string.about));
    }

    @Override
    public int getItemViewType(int position) {
        DrawerItem drawerItem = dataList.get(position);
        if (drawerItem instanceof DrawerItemDivider) {
            return TYPE_DIVIDER;
        } else if (drawerItem instanceof DrawerItemNormal) {
            return TYPE_NORMAL;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return (dataList == null || dataList.size() == 0) ? 0 : dataList.size();
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DrawerViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_DIVIDER:
                viewHolder = new DividerViewHolder(inflater.inflate(R.layout.item_drawer_divider, parent, false));
                break;
            case TYPE_NORMAL:
                viewHolder = new NormalViewHolder(inflater.inflate(R.layout.item_drawer_normal, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        final DrawerItem item = dataList.get(position);
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            final DrawerItemNormal itemNormal = (DrawerItemNormal) item;
            normalViewHolder.iv.setBackgroundResource(itemNormal.iconRes);
            normalViewHolder.tv.setText(itemNormal.titleRes);
            normalViewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.itemClick(itemNormal);
                    }
                }
            });
        } else if (holder instanceof DividerViewHolder) {
            DividerViewHolder dividerViewHolder = (DividerViewHolder) holder;
            final DrawerItemDivider drawerItemDivider = (DrawerItemDivider) item;
            dividerViewHolder.dividerView.setBackgroundColor(drawerItemDivider.color);
        }

    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener {
        void itemClick(DrawerItem drawerItemNormal);
    }

    /**
     * 统一的数据模型
     */
    public interface DrawerItem {
        boolean isHeader();

        boolean isMenuItem();
    }

    /**
     * 菜单栏中的item  配置有图片和文字
     */
    public class DrawerItemNormal implements DrawerItem {
        public int code;
        int iconRes;
        int titleRes;
        boolean showDot = false; // default not show

        void setShowDot(boolean showDot) {
            this.showDot = showDot;
        }

        DrawerItemNormal(int code, int iconRes, int titleRes) {
            this.code = code;
            this.iconRes = iconRes;
            this.titleRes = titleRes;
        }

        public int getTitleRes() {
            return titleRes;
        }

        @Override
        public boolean isHeader() {
            return false;
        }

        @Override
        public boolean isMenuItem() {
            return true;
        }
    }

    /**
     * 分割线
     */
    private class DrawerItemDivider implements DrawerItem {
        public int color;

        DrawerItemDivider(int color) {
            this.color = color;
        }

        @Override
        public boolean isHeader() {
            return false;
        }

        @Override
        public boolean isMenuItem() {
            return false;
        }
    }

    /**
     * 自定义ViewHolder
     */
    class DrawerViewHolder extends RecyclerView.ViewHolder {

        DrawerViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * (标准)普通vh
     */
    private class NormalViewHolder extends DrawerViewHolder {
        View view;
        TextView tv;
        ImageView iv;

        NormalViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            tv = itemView.findViewById(R.id.drawer_item_tv);
            iv = itemView.findViewById(R.id.drawer_item_iv);
        }
    }

    /**
     * 分割线用的 ViewHolder
     */
    private class DividerViewHolder extends DrawerViewHolder {
        View dividerView;

        DividerViewHolder(View itemView) {
            super(itemView);
            dividerView = itemView.findViewById(R.id.dividerView);
        }
    }

}
