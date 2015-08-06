package org.csware.demo.tabdemo.view;

import android.content.Context;
import android.util.Log;

import org.csware.demo.tabdemo.R;


/**
 * Created by Yu on 2015/5/21.
 * 我 Tab页
 */
class TabMinePage extends TabPage {
    TabMinePage(Context context) {
        super(context);
        Log.d("TabMinePage", "创建新的“我”页实例");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tab_mine;
    }


    @Override
    protected void init() {
        if (currView == null) return;

    }


}
