package com.alexander.fragments;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentFirst extends Fragment implements IFragmentFirstCallback{

    private EditText editText;
    private CustomBroadcastReceiver receiver;
    private IntentFilter intentFilter;
    private IFragmentThirdCallback callback;

    public static FragmentFirst newInstance(){
        FragmentFirst fragmentFirst = new FragmentFirst();
        return fragmentFirst;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.text);
        getContext().startService(MyIntentService.newIntent(getContext()));
        init();
    }

    private void init(){

        receiver = new CustomBroadcastReceiver(new ViewCallback() {
            @Override
            public void onChanged(int number) {
                editText.setText(String.valueOf(number));
            }
        });

        intentFilter = new IntentFilter("com.alexander.SEND_MESSAGES_FILTER");
    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(receiver, intentFilter, "com.alexander.SEND_MESSAGES_PERMISSION", null);
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(receiver);
    }

    @Override
    public void fragCreated() {
        try {
            callback = (IFragmentThirdCallback) getActivity().getSupportFragmentManager().findFragmentById(R.id.secondFrag).getChildFragmentManager().findFragmentByTag(FragmentThird.FRAG);
        } catch (ClassCastException e){
            Log.v("ClassCastException", e.getMessage());
        }

        initListeners();
    }

    private void initListeners(){

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                if (callback!=null) {

                    callback.recievedData(s.toString());
                }
            }
        });
    }
}
