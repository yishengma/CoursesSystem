package piratehat.coursessystem.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.CourseAdapter;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.base.BaseFragment;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.contract.ITeachContract;
import piratehat.coursessystem.presenter.TeachPresenter;
import piratehat.coursessystem.ui.StudentScoreActivity;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class TeachFragment extends BaseFragment implements ITeachContract.IView {
    @BindView(R.id.rv_course)
    RecyclerView mRvCourse;

    private List<Course> mCourses;
    private CourseAdapter mAdapter;
    private TeachPresenter mPresenter;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_teach;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

        if (isVisible) {
            mPresenter.getTeachCourse(String.valueOf(((Teacher) App.getUser()).getNo()));
        }
    }

    @Override
    protected void initData(Bundle bundle) {
        mCourses = new ArrayList<>();
        mAdapter = new CourseAdapter(mCourses, mActivity);
        mPresenter = new TeachPresenter(this);

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
                Bundle bundle = new Bundle();
                bundle.putInt("cno", course.getNo());
                bundle.putInt("tno", ((Teacher) App.getUser()).getNo());
                bundle.putString(course.getName(), "");
                StudentScoreActivity.actionStart(mActivity, StudentScoreActivity.class, bundle);
            }
        });
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTeachCourse(List list) {
        mCourses.clear();
        mCourses.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    public static TeachFragment newInstance(Bundle bundle) {
        TeachFragment fragment = new TeachFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TeachFragment newInstance() {
        return newInstance(null);
    }

}
