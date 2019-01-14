package com.alexander.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentSecond extends Fragment {

    private Button button;
    private IFragmentFirstCallback callback;

    public static FragmentSecond newInstance(){
        FragmentSecond fragmentSecond = new FragmentSecond();
        return fragmentSecond;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        try {
            this.callback = (IFragmentFirstCallback) getActivity().getSupportFragmentManager().findFragmentById(R.id.firstFrag);
        } catch (ClassCastException e){
            Log.v("ClassCastException", e.getMessage());
        }
        initListeners();
    }

    private void initViews() {
        button = getView().findViewById(R.id.button);
    }

    private void initListeners() {

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentThird fragmentThird = FragmentThird.newInstance();
                FragmentManager manager = getChildFragmentManager();
                manager.beginTransaction()
                        .add(R.id.container, fragmentThird, FragmentThird.FRAG)
                        .commitNow();
                if (callback!=null)
                    callback.fragCreated();
            }
        });
    }
}
