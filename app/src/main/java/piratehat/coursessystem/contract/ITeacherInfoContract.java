package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.bean.Teacher;

/**
 * Created by PirateHat on 2018/11/27.
 */

public interface ITeacherInfoContract {

    interface IView {
        void showError(String msg);

        void setTeachers(List list);

        void updateCallback(Teacher teacher);

        void deleteCallBack(String msg);
    }

    interface IModel {

        void getTeachers(IPresenter presenter);

        void update(IPresenter presenter, Teacher teacher);

        void delete(IPresenter presenter, String tno);
    }

    interface IPresenter {
        void showError(String msg);

        void setTeacher(List list);

        void update(Teacher teacher);

        void deleteCallBack(String msg);

        void getTeachers();

        void updateCallback(Teacher teacher);

        void delete(String tno);
    }


}
