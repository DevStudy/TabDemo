package org.csware.demo.tabdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.jfeinstein.jazzyviewpager.OutlineContainer;
import com.nineoldandroids.view.ViewHelper;

import org.csware.demo.tabdemo.view.TabPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTabActivity extends Activity {

    final static String TAG= "MainTab";

    JazzyViewPager jazzyPager;
    List<Map<String, View>> tabViews = new ArrayList<Map<String, View>>();
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        Log.d(TAG, "onCreate");

        jazzyPager =(JazzyViewPager)findViewById(R.id.jazzyPager);
        tabHost = (TabHost) findViewById(android.R.id.tabhost);



        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("0").setIndicator(createTab(getResources().getString(R.string.tab_home), 0)).setContent(android.R.id.tabcontent));
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator(createTab(getResources().getString(R.string.tab_list), 1)).setContent(android.R.id.tabcontent));
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator(createTab(getResources().getString(R.string.tab_track), 2)).setContent(android.R.id.tabcontent));
        tabHost.addTab(tabHost.newTabSpec("3").setIndicator(createTab(getResources().getString(R.string.tab_mine), 3)).setContent(android.R.id.tabcontent));
        // 点击tabHost 来切换不同的消息
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int index = Integer.parseInt(tabId);
                setTabSelectedState(index, 4);
                tabHost.getTabContentView().setVisibility(View.GONE);// 隐藏content
            }
        });
        tabHost.setCurrentTab(0);
        initJazzyPager(JazzyViewPager.TransitionEffect.Standard); //设置切换方式
    }


    /**
     * 动态创建 TabWidget 的Tab项,并设置normalLayout的alpha为1，selectedLayout的alpha为0[显示normal，隐藏selected]
     *
     * @param tabLabelText
     * @param tabIndex
     * @return
     */
    private View createTab(String tabLabelText, int tabIndex) {
        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.item_main_tab_menu, null);
        TextView normalTV = (TextView) tabIndicator.findViewById(R.id.normalTV); //未选中文本
        TextView selectedTV = (TextView) tabIndicator.findViewById(R.id.selectedTV);//已选中文本
        normalTV.setText(tabLabelText);
        selectedTV.setText(tabLabelText);
        ImageView normalImg = (ImageView) tabIndicator.findViewById(R.id.normalImg);//未选中图片
        ImageView selectedImg = (ImageView) tabIndicator.findViewById(R.id.selectedImage);//已选中图片
        switch (tabIndex) {
            case 0:
                normalImg.setImageResource(android.R.drawable.ic_menu_share);
                selectedImg.setImageResource(android.R.drawable.sym_action_call);
                break;
            case 1:
                normalImg.setImageResource(android.R.drawable.ic_dialog_email);
                selectedImg.setImageResource(android.R.drawable.sym_action_call);
                break;
            case 2:
                normalImg.setImageResource(android.R.drawable.ic_dialog_map);
                selectedImg.setImageResource(android.R.drawable.sym_action_call);
                break;
            case 3:
                normalImg.setImageResource(android.R.drawable.ic_menu_my_calendar);
                selectedImg.setImageResource(android.R.drawable.sym_action_call);
                break;
        }
        View normalLayout = tabIndicator.findViewById(R.id.normalLayout);
        normalLayout.setAlpha(1f);// 透明度显示
        View selectedLayout = tabIndicator.findViewById(R.id.selectedLayout);
        selectedLayout.setAlpha(0f);// 透明的隐藏
        Map<String, View> map = new HashMap<String, View>();
        map.put("normal", normalLayout);
        map.put("selected", selectedLayout);
        tabViews.add(map);
        return tabIndicator;
    }

    /**
     * 设置tab状态选中
     *
     * @param index
     */
    private void setTabSelectedState(int index, int tabCount) {
        for (int i = 0; i < tabCount; i++) {
            if (i == index) {
                tabViews.get(i).get("normal").setAlpha(0f);
                tabViews.get(i).get("selected").setAlpha(1f);
            } else {
                tabViews.get(i).get("normal").setAlpha(1f);
                tabViews.get(i).get("selected").setAlpha(0f);
            }
        }
        jazzyPager.setCurrentItem(index, false);// false表示 代码切换 pager 的时候不带scroll动画
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTabSelectedState(tabHost.getCurrentTab(), 4);
    }

    private void initJazzyPager(JazzyViewPager.TransitionEffect effect) {


        jazzyPager.setTransitionEffect(effect);
        jazzyPager.setAdapter(new MainAdapter());
        jazzyPager.setFadeEnabled(true); //设置是否有谈出谈出效果，还有其它的相关设置，可以到
        jazzyPager.setSlideCallBack(new JazzyViewPager.SlideCallback() {
            @Override
            public void callBack(int position, float positionOffset) {
                Map<String, View> map = tabViews.get(position);
                ViewHelper.setAlpha(map.get("selected"), positionOffset);
                ViewHelper.setAlpha(map.get("normal"), 1 - positionOffset);
            }
        });
        jazzyPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
            }

            @Override
            public void onPageScrollStateChanged(int paramInt) {
            }
        });
    }


    private class MainAdapter extends PagerAdapter {

        /**
         * 如果viewpager中的view超过3个就需要重写这个方法
         */
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            Log.d("MainAdapter", "position:" + position + "     containerName:");
            View currView = TabPage.createView(getBaseContext(), TabPage.TabPageStyle.getStyle(position));
            container.addView(currView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            jazzyPager.setObjectForPosition(currView, position);
            return currView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            container.removeView(jazzyPager.findViewFromObject(position));
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            if (view instanceof OutlineContainer) {
                return ((OutlineContainer) view).getChildAt(0) == obj;
            } else {
                return view == obj;
            }
        }
    }

}
