package com.rustfisher.animationalgorithm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rustfisher.animationalgorithm.R;

/**
 * 用于显示文字、代码等
 */
public class TextFragment extends Fragment {
    private static final String K_TEXT = "key_text";
    TextView mTv1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_text, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTv1 = view.findViewById(R.id.tv1);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String text = bundle.getString(K_TEXT);
            mTv1.setText(text);
        }
    }

    public void setText(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(K_TEXT, text);
        setArguments(bundle);
    }
}
