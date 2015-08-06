package org.csware.demo.tabdemo.view;

import android.content.Context;
import android.util.Log;

import org.csware.demo.tabdemo.R;


/**
 * Created by Yu on 2015/5/21.
 * 订单跟踪 Tab页
 */
class TabTracePage  extends TabPage {

    TabTracePage(Context context){
        super(context);
        Log.d("TabTracePage", "创建新的“订单跟踪”实例");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tab_trace;
    }


    @Override
    protected void init() {
        if (currView == null) return;

    }


}
