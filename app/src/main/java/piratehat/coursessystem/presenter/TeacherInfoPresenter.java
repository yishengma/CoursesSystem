package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.contract.ITeacherInfoContract;
import piratehat.coursessystem.model.TeacherInfoModel;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class TeacherInfoPresenter implements ITeacherInfoContract.IPresenter {
    private ITeacherInfoContract.IModel mIModel;
    private ITeacherInfoContract.IView mIView;


    public TeacherInfoPresenter(ITeacherInfoContract.IView IView) {
        mIView = IView;
        mIModel = new TeacherInfoModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void setTeacher(List list) {
        mIView.setTeachers(list);
    }

    @Override
    public void getTeachers() {
        mIModel.getTeachers(this);
    }

    @Override
    public void update(Teacher teacher) {
        mIModel.update(this, teacher);
    }

    @Override
    public void deleteCallBack(String msg) {
        mIView.deleteCallBack(msg);
    }


    @Override
    public void updateCallback(Teacher teacher) {
        mIView.updateCallback(teacher);
    }

    @Override
    public void delete(String tno) {
        mIModel.delete(this, tno);
    }
}
