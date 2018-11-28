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


        void addCallback(boolean add,Teacher teacher);

        void deleteCallBack(boolean delete,String msg);
    }

    interface IModel {

        void getTeachers(IPresenter presenter);


        void delete(IPresenter presenter, String tno);

        void add(IPresenter presenter,Teacher teacher);
    }

    interface IPresenter {

        void showError(String msg);

        void setTeacher(List list);


        void deleteCallBack(boolean delete,String msg);

        void addCallback(boolean add,Teacher teacher);

        void getTeachers();


        void delete(String tno);

        void add(Teacher teacher);
    }


}
