package org.csware.demo.tabdemo.view;

import android.content.Context;
import android.util.Log;

import org.csware.demo.tabdemo.R;


/**
 * Created by Yu on 2015/5/21.
 * 小象快运 Tab页
 */
class TabHomePage extends TabPage {
    TabHomePage(Context context) {
        super(context);
        Log.d("TabShipperPage", "创建新的“小象快运”实例");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tab_home;
    }

    /**
     * 控件初始化
     */
    @Override
    protected void init() {
        if (currView == null) return;
        Log.d("MainAdapter", "Button is null ?" );


    }

}
