package piratehat.coursessystem.model;

import android.util.Log;

import okhttp3.Call;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.INoTeachContract;
import piratehat.coursessystem.dto.CoursesDto;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class NoTeachModel implements INoTeachContract.IModel {

    private static final String TAG = "NoTeachModel";

    @Override
    public void getNoTeachCourse(final INoTeachContract.IPresenter presenter, String tno) {
        String url = Constant.TEACHER_NO_TEACH + "?tno=" + tno;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {

//                Log.e(TAG, "onResponse: " + msg);
                CoursesDto dto = GsonUtil.gsonToBean(msg, CoursesDto.class);
                presenter.setNoTeachCourse(dto.transform());
            }
        });
    }

    @Override
    public void select(final INoTeachContract.IPresenter presenter, final Course course) {
        String url = Constant.TEACHER_SELECT + "cno=" + course.getNo() + "&tno=" + String.valueOf(((Teacher) App.getUser()).getNo());
//        Log.e(TAG, "select: "+url);
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: "+msg );
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.selectCallback(result.isResult(), course);
            }
        });
    }
}
