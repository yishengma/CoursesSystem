package piratehat.coursessystem.model;

import android.util.Log;

import okhttp3.Call;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ITeachContract;
import piratehat.coursessystem.dto.CoursesDto;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class TeachModel implements ITeachContract.IModel {
//    private static final String TAG = "TeachModel";

    @Override
    public void getTeachCourse(final ITeachContract.IPresenter presenter, String tno) {
        String url = Constant.TEACHER_TEACH+"?tno="+tno;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: "+msg );
                CoursesDto dto = GsonUtil.gsonToBean(msg, CoursesDto.class);
                presenter.setTeachCourse(dto.transform());
            }
        });
    }
}
