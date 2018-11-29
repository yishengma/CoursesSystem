package piratehat.coursessystem.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import piratehat.coursessystem.bean.Result;

import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ITeacherInfoContract;

import piratehat.coursessystem.dto.PostStudent;
import piratehat.coursessystem.dto.PostTeacher;
import piratehat.coursessystem.dto.ResultDto;

import piratehat.coursessystem.dto.TeacherDtos;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class TeacherInfoModel implements ITeacherInfoContract.IModel {

    private static final String TAG = "TeacherInfoModel";

    @Override
    public void add(final ITeacherInfoContract.IPresenter presenter, final Teacher teacher) {
        String url = Constant.MANAGER_ADD;
        HashMap<String, String> map = new HashMap<>();
        map.put("info", GsonUtil.gsonToJson(new PostTeacher(teacher)));
        Log.e(TAG, "add: "+url+GsonUtil.gsonToJson(new PostTeacher(teacher)) );
        OkHttpUtil.getInstance().postAsyncFormData(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.addCallback(result.isResult(), teacher);
            }
        }, map);
    }

    @Override
    public void getTeachers(final ITeacherInfoContract.IPresenter presenter) {
        String url = Constant.MANAGER_QUERY + Constant.TEACHER;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);

                TeacherDtos teacherDtos = GsonUtil.gsonToBean(msg, TeacherDtos.class);
                if (teacherDtos.isResult()) {
                    presenter.setTeacher(teacherDtos.transform());
                } else {
                    presenter.showError(teacherDtos.getMsg());
                }
            }
        });

    }


    @Override
    public void delete(final ITeacherInfoContract.IPresenter presenter, String sno) {
        String url = Constant.MANAGER_DELETE + "type=teacher&account=" + sno;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.deleteCallBack(result.isResult(), result.getMsg());
            }
        });
    }
}
