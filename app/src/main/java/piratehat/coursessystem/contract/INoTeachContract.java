package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.bean.Course;

/**
 * Created by PirateHat on 2018/11/29.
 */

public interface INoTeachContract {

    interface IView {

        void showError(String msg);

        void setNoTeachCourse(List list);

        void selectCallback(boolean is,Course course);
    }

    interface IModel {
        void getNoTeachCourse(IPresenter presenter, String tno);

        void select(IPresenter presenter,Course course);
    }

    interface IPresenter {
        void showError(String msg);

        void setNoTeachCourse(List list);

        void getNoTeachCourse(String tno);

        void selectCallback(boolean is,Course course);

        void select(Course course);
    }
}
