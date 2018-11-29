package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.contract.INoTeachContract;
import piratehat.coursessystem.model.NoTeachModel;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class NoTeachPresenter implements INoTeachContract.IPresenter {

    private INoTeachContract.IView mIView;
    private INoTeachContract.IModel mIModel;

    public NoTeachPresenter(INoTeachContract.IView IView) {
        mIView = IView;
        mIModel = new NoTeachModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void setNoTeachCourse(List list) {
        mIView.setNoTeachCourse(list);
    }

    @Override
    public void getNoTeachCourse(String tno) {
        mIModel.getNoTeachCourse(this, tno);
    }

    @Override
    public void selectCallback(boolean is,Course course) {
        mIView.selectCallback(is,course);
    }

    @Override
    public void select(Course course) {
        mIModel.select(this, course);
    }
}
