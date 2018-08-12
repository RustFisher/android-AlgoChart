package com.rustfisher.animationalgorithm.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rustfisher.animationalgorithm.R;
import com.rustfisher.animationalgorithm.algo.controller.AlgoStore;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来显示算法列表
 */
public class AlgoReAdapter extends RecyclerView.Adapter<AlgoReAdapter.AVH> {

    private List<AlgoEntity> mDataList;
    private OnItemListener mOnItemListener;

    public AlgoReAdapter() {
        mDataList = new ArrayList<>();
    }

    public void addSortItem(AlgoStore.NUM_ARR numArr) {
        AlgoEntity sortEntity = new AlgoEntity();
        sortEntity.isNumArray = true;
        sortEntity.numArr = numArr;
        mDataList.add(sortEntity);
    }

    public void setOnItemListener(OnItemListener listener) {
        this.mOnItemListener = listener;
    }

    @Override
    public AVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_algo_type, parent, false));
    }

    @Override
    public void onBindViewHolder(AVH holder, int position) {
        final AlgoEntity entity = mDataList.get(position);
        holder.loadAlgoItem(entity);
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemListener) {
                    mOnItemListener.onItemOnClick(entity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class AVH extends RecyclerView.ViewHolder {
        View itemRoot;
        TextView nameTv;

        AVH(View itemView) {
            super(itemView);
            itemRoot = itemView;
            nameTv = itemView.findViewById(R.id.name_tv);
        }

        void loadAlgoItem(AlgoEntity algoEntity) {
            if (algoEntity.isNumArray) {
                nameTv.setText(algoEntity.numArr.getStrRes());
            }
        }

    }

    public static class AlgoEntity {
        public boolean isNumArray = false;
        public AlgoStore.NUM_ARR numArr;
    }

    public interface OnItemListener {
        void onItemOnClick(AlgoEntity algoEntity);
    }

}
