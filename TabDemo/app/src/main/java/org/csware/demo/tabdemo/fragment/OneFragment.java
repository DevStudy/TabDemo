package org.csware.demo.tabdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.csware.demo.tabdemo.R;


public class OneFragment extends Fragment {

    final static String TAG = "OneFragment";


    public OneFragment() {
        super();
        Log.w(TAG, "constructor");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"OneFragment is onDestroy");
    }

    /**
     * 可见时调用？
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.w(TAG, "onResume");
    }

    /**
     * 用户可见时调用
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.w(TAG, "setUserVisibleHint");
    }


}
