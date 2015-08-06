package org.csware.demo.tabdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.nineoldandroids.view.ViewHelper;

import org.csware.demo.tabdemo.fragment.FourFragment;
import org.csware.demo.tabdemo.fragment.OneFragment;
import org.csware.demo.tabdemo.fragment.ThreeFragment;
import org.csware.demo.tabdemo.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTabFragmentActivity extends FragmentActivity {

    final static String TAG = "TabAct";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main_tab);

        initTabBox();

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        setTabSelectedState(tabHost.getCurrentTab(), 4);
//    }

    JazzyViewPager mJazzy;
    List<Map<String, View>> tabViews = new ArrayList<Map<String, View>>();
    TabHost tabHost;

    void initTabBox() {


        mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
        initJazzyPager();
//        mJazzy.setTransitionEffect(JazzyViewPager.TransitionEffect.Standard);
//        mJazzy.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));

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




    }

    private void initJazzyPager() {


        mJazzy.setTransitionEffect(JazzyViewPager.TransitionEffect.Standard);
        mJazzy.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));
        mJazzy.setFadeEnabled(true); //设置是否有谈出谈出效果，还有其它的相关设置，可以到
        mJazzy.setSlideCallBack(new JazzyViewPager.SlideCallback() {
            @Override
            public void callBack(int position, float positionOffset) {
                Map<String, View> map = tabViews.get(position);
                ViewHelper.setAlpha(map.get("selected"), positionOffset);
                ViewHelper.setAlpha(map.get("normal"), 1 - positionOffset);
            }
        });
        mJazzy.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //Note:使用JazzyViewPager切换页面时对应的TABMenu切换
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

    /**
     * 设置tab状态选中
     *
     * @param index
     */
    private void setTabSelectedState(int index, int tabCount) {
        Log.d(TAG,"index="+index+"  tabCount:"+tabCount);
        for (int i = 0; i < tabCount; i++) {
            if (i == index) {
                tabViews.get(i).get("normal").setAlpha(0f);
                tabViews.get(i).get("selected").setAlpha(1f);
            } else {
                tabViews.get(i).get("normal").setAlpha(1f);
                tabViews.get(i).get("selected").setAlpha(0f);
            }
        }
        mJazzy.setCurrentItem(index, false);// false表示 代码切换 pager 的时候不带scroll动画
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
     * 种适配器
     */
    class TabFragmentAdapter extends FragmentPagerAdapter {

        public TabFragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        Fragment[] pages = new Fragment[]{
                new OneFragment(),
                new TwoFragment(),
                new ThreeFragment(),
                new FourFragment(),
        };


        @Override
        public Fragment getItem(int position) {
            return pages[position];
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        /**
         * 实例化可能会出现的页面实例
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i(TAG,"instantiateItem  position:"+position+"   count:"+getCount());
            Object obj = super.instantiateItem(container, position);
            mJazzy.setObjectForPosition(obj, position); //动画效果
            return obj;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object != null && ((Fragment) object).getView() == view;
        }

    }




}
