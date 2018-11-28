package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.bean.Course;

/**
 *
 * Created by PirateHat on 2018/11/28.
 */

public interface ISelectCourseContract {

    interface IView {

        void setSelectedCourses(List list);



        void unseletedCallback(boolean is,Course course);

        void showError(String msg);

    }

    interface IModel {
        void getSelectedCourses(IPresenter presenter,String sno);

        void unselect(IPresenter presenter,String sno, Course course);
    }

    interface IPresenter {
        void setSelectedCourses(List list);


        void unselectCallback(boolean is,Course course);

        void getSelectedCourses(String sno);

        void unselect(String sno, Course course);

        void showError(String msg);
    }
}


