package piratehat.coursessystem.model;

import android.util.Log;

import okhttp3.Call;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ILoginContract;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class LoginModel implements ILoginContract.IModel {

    private static final String TAG = "LoginModel";

    @Override
    public void login(final ILoginContract.IPresenter presenter, String type, String account, final String password) {
        String url = Constant.LOGIN + "?type=" + type + "&account=" + account + "&password=" + password;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
                ResultDto resultDto = GsonUtil.gsonToBean(msg, ResultDto.class);
                Result result = resultDto.transform();
                if (!result.isResult()) {
                    presenter.showError(result.getMsg());
                } else {
                    if (result.getType().equals(Constant.STUDENT)) {
                        presenter.login(result.getStudent());
                    } else if (result.getType().equals(Constant.TEACHER)){
                        presenter.login(result.getTeacher());
//                        Log.e(TAG, "onResponse: "+result.getTeacher());
                    }else {
                        presenter.login(null);
                    }

                }
            }

        });
    }
}
