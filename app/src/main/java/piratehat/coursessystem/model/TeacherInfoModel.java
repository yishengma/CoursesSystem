package piratehat.coursessystem.model;

import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.contract.ITeacherInfoContract;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class TeacherInfoModel implements ITeacherInfoContract.IModel {

    @Override
    public void getTeachers(ITeacherInfoContract.IPresenter presenter) {

    }

    @Override
    public void update(ITeacherInfoContract.IPresenter presenter, Teacher teacher) {

    }

    @Override
    public void delete(ITeacherInfoContract.IPresenter presenter, String sno) {

    }
}
