package com.quran.audio.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quran.audio.MyApplication;
import com.quran.audio.R;
import com.quran.audio.base.control.ControlActivity;
import com.quran.audio.chant.ChantFragment;
import com.quran.audio.home.HomeActivity;
import com.quran.audio.read.ReadFragment;
import com.quran.audio.teaching.TeachingFragment;
import com.quran.audio.worship.WorshipFragment;


public class MainActivity extends ControlActivity<MainPresenter> implements IMain, RadioGroup.OnCheckedChangeListener {

    private RadioGroup rgNav;
    private RadioButton rbChant, rbTeach, rbRead, rbWorship;
    private ChantFragment chantFragment;
    private TeachingFragment teachingFragment;
    private ReadFragment readFragment;
    private WorshipFragment worshipFragment;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceView(getIntent().getIntExtra("index", -1));
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewInit() {
        rgNav = (RadioGroup) findViewById(R.id.rg_nav);
        rbChant = (RadioButton) findViewById(R.id.rb_chant);
        rbTeach = (RadioButton) findViewById(R.id.rb_teach);
        rbRead = (RadioButton) findViewById(R.id.rb_read);
        rbWorship = (RadioButton) findViewById(R.id.rb_worship);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_catalog);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        LinearLayout rlSide = (LinearLayout) findViewById(R.id.ll_side);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) rlSide.getLayoutParams();
        layoutParams.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.width = (int)(dm.widthPixels * 0.6f);
        rlSide.setLayoutParams(layoutParams);
    }

    @Override
    protected void setOnListener() {
        rgNav.setOnCheckedChangeListener(this);
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public void setAdapter(CatalogAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_index:
                startActivity(HomeActivity.class);
                break;
            case R.id.rb_chant:
                replaceView(MyApplication.CHANT_INDEX);
                break;
            case R.id.rb_teach:
                replaceView(MyApplication.TEACH_INDEX);
                break;
            case R.id.rb_read:
                replaceView(MyApplication.READ_INDEX);
                break;
            case R.id.rb_worship:
                replaceView(MyApplication.WORSHIP_INDEX);
                break;
        }
    }
    private void replaceView(int index) {
        switch (index) {
            case MyApplication.CHANT_INDEX:
                if (chantFragment == null) chantFragment = new ChantFragment();
                replaceFragment(R.id.fl_layout, chantFragment);
                rbChant.setChecked(true);
                break;
            case MyApplication.TEACH_INDEX:
                if (teachingFragment == null) teachingFragment = new TeachingFragment();
                replaceFragment(R.id.fl_layout, teachingFragment);
                rbTeach.setChecked(true);
                break;
            case MyApplication.READ_INDEX:
                if (readFragment == null) readFragment = new ReadFragment();
                replaceFragment(R.id.fl_layout, readFragment);
                rbRead.setChecked(true);
                break;
            case MyApplication.WORSHIP_INDEX:
                if (worshipFragment == null) worshipFragment = new WorshipFragment();
                replaceFragment(R.id.fl_layout, worshipFragment);
                rbWorship.setChecked(true);
                break;
        }
    }
//设置触发滑动距离
//    public void setDrawerLeftEdgeSize(DrawerLayout drawerLayout, float displayWidthPercentage) {
//        if (drawerLayout == null) return;
//        try {
//            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
//            leftDraggerField.setAccessible(true);
//            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
//            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
//            edgeSizeField.setAccessible(true);
//            int edgeSize = edgeSizeField.getInt(leftDragger);
//            DisplayMetrics dm = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(dm);
//            Log.d(TAG, "setDrawerLeftEdgeSize:   " + edgeSize + "    " +dm.widthPixels+ "    " + (dm.widthPixels * displayWidthPercentage));
//            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
//        } catch (Exception e) {
//        }
//    }
}
