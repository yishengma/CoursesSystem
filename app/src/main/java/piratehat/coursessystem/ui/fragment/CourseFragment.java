package piratehat.coursessystem.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.CourseAdapter;
import piratehat.coursessystem.base.BaseFragment;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.contract.ICourseContract;
import piratehat.coursessystem.presenter.CoursePresenter;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class CourseFragment extends BaseFragment implements ICourseContract.IView {

    @BindView(R.id.rv_course)
    RecyclerView mRvCourse;
    @BindView(R.id.btn_add)
    FloatingActionButton mBtnAdd;
    private List<Course> mCourses;

    private CourseAdapter mAdapter;
    private CoursePresenter mPresenter;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_course;
    }

    @Override
    protected void initData(Bundle bundle) {

        mPresenter = new CoursePresenter(this);
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            mPresenter.getCourse();
        }
    }

    @Override
    protected void initView() {
        mCourses = new ArrayList<>();
        mRvCourse.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new CourseAdapter(mCourses, mActivity);
        mRvCourse.setAdapter(mAdapter);

    }

    @Override
    protected void initListener() {
        mAdapter.setListener(new CourseAdapter.OnClickListener() {
            @Override
            public void OnClick(Course course) {
                showNormalDialog(course);
            }
        });
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });


    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteCallback(boolean is, Course course) {
        if (is) {
            mCourses.remove(course);
            mAdapter.notifyDataSetChanged();
            Toast.makeText(mActivity,"删除成功",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void addCallback(boolean is, Course course) {
        if (is) {
            mCourses.add(course);
            mAdapter.notifyDataSetChanged();
            Toast.makeText(mActivity,"添加成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setCourses(List list) {
        mCourses.clear();
        mCourses.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    public static CourseFragment newInstance(Bundle bundle) {
        CourseFragment fragment = new CourseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CourseFragment newInstance() {
        return newInstance(null);
    }

    private void showNormalDialog(final Course course) {

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mActivity);
        normalDialog.setTitle("删除？");
        normalDialog.setMessage("确定删除编号为" + course.getNo() + "的课程？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delete(course);
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

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(mActivity);
        final View dialogView = LayoutInflater.from(mActivity)
                .inflate(R.layout.dialog_course, null);
        customizeDialog.setTitle("添加课程");

        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText no = dialogView.findViewById(R.id.et_no);

                        EditText name = dialogView.findViewById(R.id.et_name);

                        EditText school = dialogView.findViewById(R.id.et_school);

                        boolean duplicate = false;
                        for (Course s : mCourses) {
                            if (s.getNo() == Integer.valueOf(no.getText().toString())) {
                                Toast.makeText(mActivity, "课程重复", Toast.LENGTH_SHORT).show();
                                duplicate = true;
                                break;
                            }
                        }

                        if (!duplicate && isNull(no, name, school)) {
                            Course course = new Course();
                            course.setName(name.getText().toString());
                            course.setSchool(school.getText().toString());
                            course.setNo(Integer.valueOf(no.getText().toString()));
                            mPresenter.add(course);
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
                Toast.makeText(mActivity, "不能为空！", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
