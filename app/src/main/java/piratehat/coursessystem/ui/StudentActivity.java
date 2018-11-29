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
import butterknife.ButterKnife;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.ViewPagerAdapter;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.base.BaseActivity;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.IStudentContract;
import piratehat.coursessystem.presenter.StudentPresenter;
import piratehat.coursessystem.ui.fragment.SelectCourseFragment;
import piratehat.coursessystem.ui.fragment.StudentInfoFragment;
import piratehat.coursessystem.ui.fragment.TeacherInfoFragment;
import piratehat.coursessystem.ui.fragment.UnSelectedCourseFragment;

public class StudentActivity extends BaseActivity implements IStudentContract.IView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tl_navigation)
    TabLayout mTlNavigation;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private StudentPresenter mPresenter;

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
        mPresenter = new StudentPresenter(this);
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
    public void UpdateCallback(boolean is, Student student) {
        if (is) {
            App.setUser(student);
            Toast.makeText(this, "更新成功！", Toast.LENGTH_SHORT).show();

        }

    }

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(this);
        final View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_student_info, null);
        customizeDialog.setTitle("学生信息");

        customizeDialog.setView(dialogView);
        Student student = App.getUser();

        final EditText no = dialogView.findViewById(R.id.et_no);

        final EditText name = dialogView.findViewById(R.id.et_name);

        final EditText age = dialogView.findViewById(R.id.et_age);

        final EditText sex = dialogView.findViewById(R.id.et_sex);

        final EditText school = dialogView.findViewById(R.id.et_school);
        no.setText(String.valueOf(student.getNo()));
        no.setEnabled(false);

        name.setText(student.getName());
        age.setText(String.valueOf(student.getAge()));
        sex.setText(student.getSex());
        school.setText(student.getSchool());

        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (isNull( name, age, sex, school)) {
                            Student student = new Student();
                            student.setAge(Integer.valueOf(age.getText().toString()));
                            student.setName(name.getText().toString());
                            student.setSchool(school.getText().toString());
                            student.setSex(sex.getText().toString());
                            student.setNo(Integer.valueOf(no.getText().toString()));
                            mPresenter.Update(student);
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

    private boolean isNull(String... strings) {

        for (String string : strings) {
            if (TextUtils.isEmpty(string)) {
                return false;
            }
        }
        return true;
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
