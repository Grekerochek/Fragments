package com.alexander.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentThird extends Fragment implements IFragmentThirdCallback {

    public static final String FRAG = "FRAG";

    private TextView textView;

    public static FragmentThird newInstance(){
        FragmentThird fragmentThird = new FragmentThird();
        return fragmentThird;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textView);
    }

    @Override
    public void recievedData(String data) {
        textView.setText(data);
    }
}
