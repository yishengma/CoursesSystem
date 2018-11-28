package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.contract.IStudentInfoContract;
import piratehat.coursessystem.model.StudentInfoModel;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class StudentInfoPresenter implements IStudentInfoContract.IPresenter {
    private IStudentInfoContract.IModel mIModel;
    private IStudentInfoContract.IView mIView;

    public StudentInfoPresenter(IStudentInfoContract.IView IView) {
        mIView = IView;
        mIModel = new StudentInfoModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void setStudents(List list) {
        mIView.setStudents(list);
    }

    @Override
    public void add(Student student) {
        mIModel.add(this,student);
    }

    @Override
    public void addCallback(boolean add, Student student) {
        mIView.addCallback(add, student);
    }

    @Override
    public void deleteCallBack(boolean delete, String msg) {
        mIView.deleteCallBack(delete, msg);
    }

    @Override
    public void getStudents() {
        mIModel.getStudents(this);
    }


    @Override
    public void delete(String sno) {
        mIModel.delete(this, sno
        );
    }
}
