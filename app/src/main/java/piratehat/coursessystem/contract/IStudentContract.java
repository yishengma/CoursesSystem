package piratehat.coursessystem.contract;

import piratehat.coursessystem.bean.Student;

/**
 * Created by PirateHat on 2018/11/29.
 */

public interface IStudentContract {

    interface IView{
        void showError(String msg);

        void UpdateCallback(boolean is,Student student);
    }

    interface IModel{

        void Update(IPresenter presenter,Student student);
    }

    interface IPresenter{
        void showError(String msg);

        void UpdateCallback(boolean is,Student student);

        void Update(Student student);
    }
}
