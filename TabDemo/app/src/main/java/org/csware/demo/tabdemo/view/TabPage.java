package org.csware.demo.tabdemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Yu on 2015/5/21.
 * <p/>
 * 主界面左右滑动切换页基类
 */
public abstract class TabPage {

    protected Context context;
    protected View currView = null;

    TabPage(Context context) {
        this.context = context;
        //currView = View.inflate(context, R.layout.tab_home, null);
        //currView = context.getLayoutInflater().inflate(R.layout.tab_home,new LinearLayout(context) );
        //currView = LayoutInflater.from(context).inflate(R.layout.tab_home, null);
        LayoutInflater factory = LayoutInflater.from(context);
        currView = factory.inflate(getLayoutId(), null);
    }


    /**
     * 创建一个可用的Tab 页
     */
    public static View createView(Context context, TabPageStyle style) {
        switch (style) {
            case DELIVER:
                return new TabHomePage(context).getView();
            case ORDER:
                return new TabListPage(context).getView();
            case TRACE:
                return new TabTracePage(context).getView();
            case MINE:
                return new TabMinePage(context).getView();
        }
        return null;
    }

    protected abstract int getLayoutId();

    protected abstract void init();


    protected View getView() {
        init();
        return currView;
    }


    public enum TabPageStyle {
        /**
         * 小象快运
         */
        DELIVER("Deliver", 0),
        /**
         * 我的订单
         */
        ORDER("Order", 1),
        /**
         * 订单跟踪
         */
        TRACE("Trace", 2),

        /**
         * 我
         */
        MINE("Mine", 3);

        // 成员变量
        private String name;
        private int index;

        // 构造方法
        TabPageStyle(String name, int index) {
            this.name = name;
            this.index = index;
        }

        /**
         * 设定索引id，直接获得结果
         */
        public static TabPageStyle getStyle(int index) {
            switch (index) {
                case 0:
                    return TabPageStyle.DELIVER;
                case 1:
                    return TabPageStyle.ORDER;
                case 2:
                    return TabPageStyle.TRACE;
                case 3:
                    return TabPageStyle.MINE;
            }
            return null;
        }

    }

}


