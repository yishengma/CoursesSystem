package piratehat.coursessystem.contract;

import java.util.List;

import piratehat.coursessystem.app.App;

/**
 * Created by PirateHat on 2018/11/29.
 */

public interface ITeachContract {
    interface IView {

        void showError(String msg);

        void setTeachCourse(List list);
    }

    interface IModel {

        void getTeachCourse(IPresenter presenter, String tno);
    }

    interface IPresenter {
        void showError(String msg);

        void setTeachCourse(List list);

        void getTeachCourse(String tno);

    }
}
