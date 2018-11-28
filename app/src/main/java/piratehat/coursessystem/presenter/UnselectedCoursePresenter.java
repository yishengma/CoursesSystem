package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.contract.IUnselectedCourseContract;
import piratehat.coursessystem.model.UnselectedCourseModel;

/**
 *
 * Created by PirateHat on 2018/11/28.
 */

public class UnselectedCoursePresenter implements IUnselectedCourseContract.IPresenter {

    private IUnselectedCourseContract.IView mIView;
    private IUnselectedCourseContract.IModel mIModel;

    public UnselectedCoursePresenter(IUnselectedCourseContract.IView IView) {
        mIView = IView;
        mIModel = new UnselectedCourseModel();

    }
    @Override
    public void setUnselectedCourses(List list) {
        mIView.setUnselectedCourses(list);
    }

    @Override
    public void selectCallback(boolean is,Course course) {
        mIView.selectCallback(is,course);
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void getUnselectedCourses(String sno) {
        mIModel.getUnselectedCourses(this, sno);
    }

    @Override
    public void select(String sno, Course course) {
        mIModel.select(this, sno, course);
    }
}
