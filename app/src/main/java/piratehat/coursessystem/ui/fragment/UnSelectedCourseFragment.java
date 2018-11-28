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
import piratehat.coursessystem.adapter.SelectCourseAdapter;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.base.BaseFragment;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.IUnselectedCourseContract;
import piratehat.coursessystem.presenter.UnselectedCoursePresenter;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class UnSelectedCourseFragment extends BaseFragment implements IUnselectedCourseContract.IView {

    @BindView(R.id.rv_course)
    RecyclerView mRvCourse;

    private List<Course> mCourses;
    private int mType;
    private SelectCourseAdapter mAdapter;
    private UnselectedCoursePresenter mPresenter;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_select_course;
    }

    @Override
    protected void initData(Bundle bundle) {
        mPresenter = new UnselectedCoursePresenter(this);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            mPresenter.getUnselectedCourses(String.valueOf(((Student) App.getUser()).getNo()));
        }
    }

    @Override
    protected void initView() {
        mCourses = new ArrayList<>();
        mRvCourse.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new SelectCourseAdapter(mCourses, mActivity, Constant.UNSELECTED);
        mRvCourse.setAdapter(mAdapter);

    }

    @Override
    protected void initListener() {
        mAdapter.setListener(new SelectCourseAdapter.OnClickListener() {
            @Override
            public void OnClick(Course course) {
                showDialog(course);
            }
        });
    }


    @Override
    public void setUnselectedCourses(List list) {
        mCourses.clear();
        mCourses.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectCallback(boolean is, Course course) {
        if (is){
            mCourses.remove(course);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();

    }

    private void showDialog(final Course course) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mActivity);
        normalDialog.setTitle("选修");
        normalDialog.setMessage("确定选修" + course.getNo() + " " + course.getName() + "？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.select(String.valueOf(((Student) App.getUser()).getNo()), course);
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

    public static UnSelectedCourseFragment newInstance(Bundle bundle) {
        UnSelectedCourseFragment fragment = new UnSelectedCourseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static UnSelectedCourseFragment newInstance() {
        return newInstance(null);
    }


}
