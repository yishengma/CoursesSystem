package piratehat.coursessystem.presenter;

import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.contract.IStudentContract;
import piratehat.coursessystem.model.StudentModel;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class StudentPresenter implements IStudentContract.IPresenter {

    private IStudentContract.IModel mIModel;
    private IStudentContract.IView mIView;

    public StudentPresenter(IStudentContract.IView IView) {
        mIView = IView;
        mIModel = new StudentModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void UpdateCallback(boolean is, Student student) {
        mIView.UpdateCallback(is, student);
    }

    @Override
    public void Update(Student student) {
        mIModel.Update(this, student);
    }
}
