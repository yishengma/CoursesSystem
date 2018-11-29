package piratehat.coursessystem.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.CourseAdapter;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.base.BaseFragment;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.contract.INoTeachContract;
import piratehat.coursessystem.presenter.NoTeachPresenter;
import piratehat.coursessystem.presenter.TeachPresenter;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class NoTeachFragment extends BaseFragment implements INoTeachContract.IView {

    @BindView(R.id.rv_course)
    RecyclerView mRvCourse;

    private List<Course> mCourses;
    private CourseAdapter mAdapter;
    private NoTeachPresenter mPresenter;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_teach;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            mPresenter.getNoTeachCourse(String.valueOf(((Teacher) App.getUser()).getNo()));
        }
    }

    @Override
    protected void initData(Bundle bundle) {
        mCourses = new ArrayList<>();
        mAdapter = new CourseAdapter(mCourses, mActivity);
        mPresenter = new NoTeachPresenter(this);

    }

    @Override
    protected void initView() {
        mRvCourse.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
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
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setNoTeachCourse(List list) {
        mCourses.clear();
        mCourses.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectCallback(boolean is, Course course) {
        if (is) {
            mCourses.remove(course);
            mAdapter.notifyDataSetChanged();
            Toast.makeText(mActivity, "选择成功！", Toast.LENGTH_SHORT);

        }
    }

    public static NoTeachFragment newInstance(Bundle bundle) {
        NoTeachFragment fragment = new NoTeachFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static NoTeachFragment newInstance() {
        return newInstance(null);
    }


    private void showNormalDialog(final Course course) {

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mActivity);
        normalDialog.setTitle("教授？");
        normalDialog.setMessage("确定教授" + course.getName() + "课程？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.select(course);
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
