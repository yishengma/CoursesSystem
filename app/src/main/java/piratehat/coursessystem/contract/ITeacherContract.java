package piratehat.coursessystem.contract;


import piratehat.coursessystem.bean.Teacher;

/**
 *
 * Created by PirateHat on 2018/11/29.
 */

public interface ITeacherContract {


    interface IView {
        void showError(String msg);

        void UpdateCallback(boolean is,Teacher teacher);
    }

    interface IModel {

        void Update(IPresenter presenter, Teacher teacher);
    }

    interface IPresenter {
        void showError(String msg);

        void UpdateCallback(boolean is,Teacher teacher);

        void Update(Teacher teacher);
    }


}
