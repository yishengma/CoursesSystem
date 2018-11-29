package piratehat.coursessystem.presenter;

import java.util.List;

import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.contract.IStudentScoreContract;
import piratehat.coursessystem.model.StudentScoreModel;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class StudentScorePresenter implements IStudentScoreContract.IPresenter {

    private IStudentScoreContract.IModel mIModel;
    private IStudentScoreContract.IView mIView;

    public StudentScorePresenter(IStudentScoreContract.IView IView) {
        mIView = IView;
        mIModel = new StudentScoreModel();
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
    public void setScoresCallBack() {
        mIView.setScoresCallBack();
    }

    @Override
    public void getStudents(int tno, int cno) {
        mIModel.getStudents(this, tno, cno);
    }

    @Override
    public void update(List<Student> list,int cno) {
        mIModel.update(this, list,cno);
    }
}
