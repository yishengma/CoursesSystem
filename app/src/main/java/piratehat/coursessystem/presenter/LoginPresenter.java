package piratehat.coursessystem.presenter;

import piratehat.coursessystem.contract.ILoginContract;
import piratehat.coursessystem.model.LoginModel;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class LoginPresenter implements ILoginContract.IPresenter {
    private ILoginContract.IView mIView;
    private ILoginContract.IModel mIModel;

    public LoginPresenter(ILoginContract.IView IView) {
        mIView = IView;
        mIModel = new LoginModel();
    }

    @Override
    public void showError(String msg) {
        mIView.showError(msg);
    }

    @Override
    public void login(Object o) {

        mIView.login(o);
    }

    @Override
    public void login(String type, String account, String password) {
        mIModel.login(this,type,account,password);
    }
}
