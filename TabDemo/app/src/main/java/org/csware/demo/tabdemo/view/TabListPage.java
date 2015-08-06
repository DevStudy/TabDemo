package org.csware.demo.tabdemo.view;

import android.content.Context;
import android.util.Log;

import org.csware.demo.tabdemo.R;


/**
 * Created by Yu on 2015/5/21.
 * 我的订单 Tab页
 */
class TabListPage extends TabPage {

    TabListPage(Context context){
        super(context);
        Log.d("TabOrderPage", "创建新的“我的订单”实例");
    }

    @Override
    protected void init() {
        if (currView == null) return;

    }



    @Override
    protected int getLayoutId() {
        return R.layout.tab_list;
    }







}
