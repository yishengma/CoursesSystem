package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.contract.ICourseContract;
import piratehat.coursessystem.model.CourseModel;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class CoursePresenter implements ICourseContract.IPresenter {

    private ICourseContract.IView mIView;
    private ICourseContract.IModel mIModel;

    public CoursePresenter(ICourseContract.IView IView) {
        mIView = IView;
        mIModel = new CourseModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void deleteCallback(boolean is,Course course) {
        mIView.deleteCallback(is,course);
    }

    @Override
    public void addCallback(boolean is,Course course) {
        mIView.addCallback(is,course);
    }

    @Override
    public void setCourses(List list) {
        mIView.setCourses(list);
    }

    @Override
    public void getCourse() {
        mIModel.getCourse(this);
    }

    @Override
    public void delete(Course course) {
        mIModel.delete(this, course);
    }

    @Override
    public void add(Course course) {
        mIModel.add(this,course);
    }
}
