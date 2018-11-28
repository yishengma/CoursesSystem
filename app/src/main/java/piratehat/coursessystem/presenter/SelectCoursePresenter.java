package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.contract.ISelectCourseContract;
import piratehat.coursessystem.model.SelectedCourseModel;

/**
 *
 * Created by PirateHat on 2018/11/28.
 */

public class SelectCoursePresenter implements ISelectCourseContract.IPresenter {

    private ISelectCourseContract.IView mIView;
    private ISelectCourseContract.IModel mIModel;


    public SelectCoursePresenter(ISelectCourseContract.IView IView) {
        mIView = IView;
        mIModel = new SelectedCourseModel();
    }

    @Override
    public void setSelectedCourses(List list) {
        mIView.setSelectedCourses(list);
    }

//    @Override
//    public void select(Course course) {
//        mIView.select(course);
//    }


    @Override
    public void unselectCallback(boolean is,Course course) {
        mIView.unseletedCallback(is,course);
    }

    @Override
    public void getSelectedCourses(String sno) {
        mIModel.getSelectedCourses(this, sno);
    }

//    @Override
//    public void select(String sno, Course course) {
//        mIModel.select(this, sno, course);
//    }

    @Override
    public void unselect(String sno, Course course) {
        mIModel.unselect(this, sno, course);
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }
}
