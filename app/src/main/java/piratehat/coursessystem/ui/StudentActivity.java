package piratehat.coursessystem.ui;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.ViewPagerAdapter;
import piratehat.coursessystem.base.BaseActivity;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.ui.fragment.SelectCourseFragment;
import piratehat.coursessystem.ui.fragment.StudentInfoFragment;
import piratehat.coursessystem.ui.fragment.TeacherInfoFragment;
import piratehat.coursessystem.ui.fragment.UnSelectedCourseFragment;

public class StudentActivity extends BaseActivity {

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
        return R.layout.activity_student;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mToolBar.setTitle(Constant.STUDENT);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(SelectCourseFragment.newInstance());
        viewPagerAdapter.addFragment(UnSelectedCourseFragment.newInstance());


        mVpContent.setAdapter(viewPagerAdapter);
        mTlNavigation.setupWithViewPager(mVpContent);


        mTlNavigation.getTabAt(0).setText("已选课程");
        mTlNavigation.getTabAt(1).setText("未选课程");
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initListener() {

    }


}
