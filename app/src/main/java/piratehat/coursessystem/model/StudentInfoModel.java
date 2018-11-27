package piratehat.coursessystem.model;

import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.contract.IStudentInfoContract;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class StudentInfoModel implements IStudentInfoContract.IModel {

    @Override
    public void getStudents(IStudentInfoContract.IPresenter presenter) {

    }

    @Override
    public void update(IStudentInfoContract.IPresenter presenter, Student student) {

    }

    @Override
    public void delete(IStudentInfoContract.IPresenter presenter, String sno) {

    }
}
