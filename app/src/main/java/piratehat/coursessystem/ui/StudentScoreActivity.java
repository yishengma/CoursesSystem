package piratehat.coursessystem.ui;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.ScoreAdapter;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.base.BaseActivity;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.IStudentScoreContract;
import piratehat.coursessystem.presenter.StudentScorePresenter;

public class StudentScoreActivity extends BaseActivity implements IStudentScoreContract.IView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.rv_student)
    RecyclerView mRvStudent;

    private List<Student> mStudents;
    private ScoreAdapter mAdapter;
    private StudentScorePresenter mPresenter;
    private int mCno;


    @Override
    protected int setResId() {
        return R.layout.activity_student_score;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

    }


    @Override
    protected void initData(Bundle bundle) {
        mStudents = new ArrayList<>();
        mAdapter = new ScoreAdapter(this, mStudents);
        mRvStudent.setAdapter(mAdapter);
        mRvStudent.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        int tno = bundle.getInt("tno");
        mCno = bundle.getInt("cno");
        mPresenter = new StudentScorePresenter(this);
        mPresenter.getStudents(tno,mCno );
        mToolBar.setTitle(bundle.getString("name"));



    }
        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_commit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.commit:
                showDialog();
                break;
            case android.R.id.home: {
                finish();

            }
//


        }
        return false;

    }
    @Override
    protected void initListener() {

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setStudents(List list) {
        mStudents.clear();
        mStudents.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setScoresCallBack() {
        Toast.makeText(this, "设置成功！", Toast.LENGTH_SHORT).show();
    }

    private void showDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setTitle("提交");
        normalDialog.setMessage("确定提交当前分数？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.update(mStudents,mCno);
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        normalDialog.show();
    }

}
