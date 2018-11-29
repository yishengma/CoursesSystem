package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.contract.ITeacherContract;
import piratehat.coursessystem.contract.ITeacherInfoContract;
import piratehat.coursessystem.model.TeacherModel;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class TeacherPresenter implements ITeacherContract.IPresenter {

    private ITeacherContract.IView mIView;
    private ITeacherContract.IModel mIModel;

    public TeacherPresenter(ITeacherContract.IView IView) {
        mIView = IView;
        mIModel = new TeacherModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void UpdateCallback(boolean is, Teacher teacher) {
        mIView.UpdateCallback(is, teacher);
    }

    @Override
    public void Update(Teacher teacher) {
        mIModel.Update(this, teacher);
    }
}
