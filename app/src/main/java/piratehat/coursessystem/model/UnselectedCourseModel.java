package piratehat.coursessystem.model;

import android.util.Log;

import okhttp3.Call;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.IUnselectedCourseContract;
import piratehat.coursessystem.dto.SelectCoursesDto;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 *
 * Created by PirateHat on 2018/11/28.
 */

public class UnselectedCourseModel implements IUnselectedCourseContract.IModel {
    private static final String TAG = "UnselectedCourseModel";
    @Override
    public void getUnselectedCourses(final IUnselectedCourseContract.IPresenter presenter, String sno) {
        String url  = Constant.STUDENT_COURSE+"type=unselected&sno="+sno;
//        Log.e(TAG, "getUnselectedCourses: "+url );
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: "+msg);
                SelectCoursesDto selectCoursesDto = GsonUtil.gsonToBean(msg,SelectCoursesDto.class);
                presenter.setUnselectedCourses(selectCoursesDto.transform());
            }
        });
    }

    @Override
    public void select(final IUnselectedCourseContract.IPresenter presenter, String sno, final Course course) {
       String url = Constant.STUDENT_SELECT+"sno="+sno+"&cno="+course.getNo();
       OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
           @Override
           public void onError(Call call, Exception e) {
               presenter.showError(e.getMessage());
           }

           @Override
           public void onResponse(String msg) {
//               Log.e(TAG, "onResponse: "+msg );
               Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
               presenter.selectCallback(result.isResult(), course);
           }
       });

    }

}
