package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.bean.Course;

/**
 * Created by PirateHat on 2018/11/28.
 */

public interface ICourseContract {

    interface IView {
        void showError(String msg);

        void deleteCallback(boolean is,Course course);

        void addCallback(boolean is,Course course);

        void setCourses(List list);
    }

    interface IModel {

        void getCourse(IPresenter presenter);

        void delete(IPresenter presenter, Course course);

        void add(IPresenter presenter, Course course);

    }


    interface IPresenter {
        void showError(String msg);

        void deleteCallback(boolean is,Course course);

        void addCallback(boolean is,Course course);

        void setCourses(List list);

        void getCourse();

        void delete(Course course);

        void add(Course course);

    }
}

