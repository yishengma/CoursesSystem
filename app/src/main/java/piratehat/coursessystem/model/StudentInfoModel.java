package piratehat.coursessystem.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.IStudentInfoContract;
import piratehat.coursessystem.dto.PostStudent;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.dto.StudentDots;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class StudentInfoModel implements IStudentInfoContract.IModel {
    private static final String TAG = "StudentInfoModel";

    @Override
    public void getStudents(final IStudentInfoContract.IPresenter presenter) {
        String url = Constant.MANAGER_QUERY + Constant.STUDENT;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
                StudentDots studentDots = GsonUtil.gsonToBean(msg, StudentDots.class);
                if (studentDots.isResult()) {
                    presenter.setStudents(studentDots.transform());
                } else {
                    presenter.showError(studentDots.getMsg());
                }
            }
        });
    }

    @Override
    public void add(final IStudentInfoContract.IPresenter presenter, final Student student) {
        String url = Constant.MANAGER_ADD;
        Map<String, String> map = new HashMap<>();
        map.put("info", GsonUtil.gsonToJson(new PostStudent(student)));
//        Log.e(TAG, "add: "+url+GsonUtil.gsonToJson(new PostStudent(student)) );
        OkHttpUtil.getInstance().postAsyncFormData(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.addCallback(result.isResult(),student);

            }
        },map);
    }

    @Override
    public void delete(final IStudentInfoContract.IPresenter presenter, String sno) {
        String url = Constant.MANAGER_DELETE + "type=student&account=" + sno;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
                //{"msg":"删除成功！","result":true,"type":"manager"}

                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.deleteCallBack(result.isResult(), result.getMsg());

            }
        });

    }

    @Override
    public void search(final IStudentInfoContract.IPresenter presenter, String str) {
        String url = Constant.IP_ADDRESS + "/web/student/like?str=" + str;
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
                StudentDots studentDots = GsonUtil.gsonToBean(msg, StudentDots.class);
                if (studentDots.isResult()) {
                    presenter.setStudents(studentDots.transform());
                } else {
                    presenter.showError(studentDots.getMsg());
                }
            }
        });
    }
}
