package piratehat.coursessystem.ui;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.ViewPagerAdapter;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.base.BaseActivity;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ITeacherContract;
import piratehat.coursessystem.presenter.TeacherPresenter;
import piratehat.coursessystem.ui.fragment.NoTeachFragment;
import piratehat.coursessystem.ui.fragment.SelectCourseFragment;
import piratehat.coursessystem.ui.fragment.TeachFragment;
import piratehat.coursessystem.ui.fragment.UnSelectedCourseFragment;

public class TeacherActivity extends BaseActivity implements ITeacherContract.IView {
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tl_navigation)
    TabLayout mTlNavigation;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private TeacherPresenter mPresenter;

    @Override
    protected int setResId() {
        return R.layout.activity_teacher;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mToolBar.setTitle(Constant.TEACHER);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(TeachFragment.newInstance());
        viewPagerAdapter.addFragment(NoTeachFragment.newInstance());


        mVpContent.setAdapter(viewPagerAdapter);
        mTlNavigation.setupWithViewPager(mVpContent);


        mTlNavigation.getTabAt(0).setText("教授课程");
        mTlNavigation.getTabAt(1).setText("可教课程");
    }

    @Override
    protected void initData(Bundle bundle) {
        mPresenter = new TeacherPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.user:
                showCustomizeDialog();
                break;


        }
        return false;

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void UpdateCallback(boolean is, Teacher teacher) {
        if (is) {
            App.setUser(teacher);
            Toast.makeText(this, "更新成功！", Toast.LENGTH_SHORT).show();

        }
    }

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_teacher_info, null);
        customizeDialog.setTitle("教师信息");
        customizeDialog.setView(dialogView);

        Teacher teacher1 = App.getUser();
        final EditText no = dialogView.findViewById(R.id.et_no);
        no.setText(String.valueOf(teacher1.getNo()));
        no.setEnabled(false);


        final EditText name = dialogView.findViewById(R.id.et_name);

        final EditText tel = dialogView.findViewById(R.id.et_tel);

        final EditText sex = dialogView.findViewById(R.id.et_sex);

        final EditText school = dialogView.findViewById(R.id.et_school);


        name.setText(teacher1.getName());
        tel.setText(teacher1.getTel());
        sex.setText(teacher1.getSex());
        school.setText(teacher1.getSchool());

        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (isNull(name, tel, sex, school)) {
                            Teacher teacher = new Teacher();
                            teacher.setTel(tel.getText().toString());
                            teacher.setName(name.getText().toString());
                            teacher.setSchool(school.getText().toString());
                            teacher.setSex(sex.getText().toString());
                            teacher.setNo(Integer.valueOf(no.getText().toString()));
                            mPresenter.Update(teacher);
                        }

                    }

                });
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        customizeDialog.show();
    }

    private boolean isNull(EditText... editTexts) {

        for (EditText string : editTexts) {
            if (TextUtils.isEmpty(string.getText().toString())) {
                Toast.makeText(this, "不能为空！", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
