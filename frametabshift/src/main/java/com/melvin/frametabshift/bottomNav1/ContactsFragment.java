package com.melvin.frametabshift.bottomNav1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melvin.frametabshift.R;


public class ContactsFragment extends Fragment {
    private static final String TAG = "ContactsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,"onCreateView");
        return inflater.inflate(R.layout.tab_shift_fragment_contacts, container, false);
    }


}
