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



        void addCallback(boolean add,Student student);

        void deleteCallBack(boolean delete,String msg);
    }

    interface IModel {

        void getStudents(IPresenter presenter);


        void add(IPresenter presenter,Student student);

        void delete(IPresenter presenter, String sno);

        void search(IPresenter presenter,String str);
    }

    interface IPresenter {
        void showError(String msg);

        void setStudents(List list);


        void add(Student student);

        void deleteCallBack(boolean delete,String msg);

        void getStudents();


        void addCallback(boolean add,Student student);

        void delete(String sno);

        void search(String str);
    }


}
