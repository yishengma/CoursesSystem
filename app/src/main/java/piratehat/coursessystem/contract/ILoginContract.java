package piratehat.coursessystem.contract;

/**
 * Created by PirateHat on 2018/11/27.
 */

public interface ILoginContract {

    interface IView {
        void showError(String msg);

        void login(Object o);
    }


    interface IModel {
        void login(IPresenter presenter, String type, String account, String password);

    }


    interface IPresenter {
        void showError(String msg);

        void login(Object o);

        void login(String type, String account, String password);

    }
}
