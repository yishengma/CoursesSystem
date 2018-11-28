package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.bean.Course;

/**
 * Created by PirateHat on 2018/11/28.
 */

public interface IUnselectedCourseContract  {

    interface IView{

        void setUnselectedCourses(List list);

        void selectCallback(boolean is,Course course);

        void showError(String msg);

    }

    interface IModel{

        void getUnselectedCourses(IPresenter presenter,String sno);

        void select(IPresenter presenter,String sno,Course course);

    }

    interface IPresenter{

        void setUnselectedCourses(List list);

        void selectCallback(boolean is,Course course);

        void showError(String msg);

        void getUnselectedCourses(String sno);

        void select(String sno,Course course);

    }
}
