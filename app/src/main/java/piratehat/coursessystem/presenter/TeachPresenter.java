package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.contract.ITeachContract;
import piratehat.coursessystem.model.TeachModel;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class TeachPresenter implements ITeachContract.IPresenter {

    private ITeachContract.IView mIView;
    private ITeachContract.IModel mIModel;

    public TeachPresenter(ITeachContract.IView IView) {
        mIView = IView;
        mIModel = new TeachModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void setTeachCourse(List list) {
        mIView.setTeachCourse(list);
    }

    @Override
    public void getTeachCourse(String tno) {
        mIModel.getTeachCourse(this, tno);
    }
}
