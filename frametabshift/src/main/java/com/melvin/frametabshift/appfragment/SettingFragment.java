package com.melvin.frametabshift.appfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melvin.frametabshift.R;


public class SettingFragment extends Fragment {

    private static final String TAG = "SettingFragment";
    public SettingFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,"onCreateView");
        return inflater.inflate(R.layout.tab_shift_fragment_setting, container, false);
    }





}
