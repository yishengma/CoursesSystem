package piratehat.coursessystem.ui;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.ViewPagerAdapter;
import piratehat.coursessystem.base.BaseActivity;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.ui.fragment.CourseFragment;
import piratehat.coursessystem.ui.fragment.StudentInfoFragment;
import piratehat.coursessystem.ui.fragment.TeacherInfoFragment;

public class ManagerActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tl_navigation)
    TabLayout mTlNavigation;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    protected int setResId() {
        return R.layout.activity_manager;
    }

    @Override
    protected void initView() {

        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mToolBar.setTitle(Constant.MANAGER);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(StudentInfoFragment.newInstance());
        viewPagerAdapter.addFragment(TeacherInfoFragment.newInstance());
        viewPagerAdapter.addFragment(CourseFragment.newInstance());


        mVpContent.setAdapter(viewPagerAdapter);
        mTlNavigation.setupWithViewPager(mVpContent);


        mTlNavigation.getTabAt(0).setText(Constant.STUDENT);
        mTlNavigation.getTabAt(1).setText(Constant.TEACHER);
        mTlNavigation.getTabAt(2).setText(Constant.COURSE);


    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }


}
