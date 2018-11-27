package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.bean.Student;

/**
 * Created by PirateHat on 2018/11/27.
 */

public interface IStudentInfoContract {

    interface IView {
        void showError(String msg);

        void setStudents(List list);

        void updateCallback(Student student);

        void deleteCallBack(String msg);
    }

    interface IModel {

        void getStudents(IPresenter presenter);

        void update(IPresenter presenter, Student student);

        void delete(IPresenter presenter, String sno);
    }

    interface IPresenter {
        void showError(String msg);

        void setStudents(List list);

        void update(Student student);

        void deleteCallBack(String msg);

        void getStudents();

        void updateCallback(Student student);

        void delete(String sno);
    }


}
