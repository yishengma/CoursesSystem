package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.bean.Student;

/**
 * Created by PirateHat on 2018/11/29.
 */

public interface IStudentScoreContract  {

    interface IView{
        void showError(String msg);

        void setStudents(List list);

        void setScoresCallBack();

    }

    interface IModel{

        void getStudents(IPresenter presenter,int tno,int cno);

        void update(IPresenter presenter,List<Student> list,int cno);


    }

    interface IPresenter{

        void showError(String msg);

        void setStudents(List list);

        void setScoresCallBack();

        void getStudents(int tno,int cno);

        void update(List<Student> list,int cno);
    }
}
