package piratehat.coursessystem.model;

import android.util.Log;

import java.util.HashMap;

import javax.xml.transform.OutputKeys;

import okhttp3.Call;
import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ICourseContract;

import piratehat.coursessystem.dto.CoursesDto;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class CourseModel implements ICourseContract.IModel {
    private static final String TAG = "CourseModel";

    @Override
    public void getCourse(final ICourseContract.IPresenter presenter) {
        String url = Constant.MANAGER_QUERY + "course";
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
                CoursesDto dto = GsonUtil.gsonToBean(msg, CoursesDto.class);
                presenter.setCourses(dto.transform());
            }
        });
    }

    @Override
    public void delete(final ICourseContract.IPresenter presenter, final Course course) {

        String url = Constant.MANAGER_DELETE_COURSE;
        HashMap<String, String> map = new HashMap<>();
        map.put("info", GsonUtil.gsonToJson(new CoursePost(course.getName(), course.getNo(), course.getSchool())));
        OkHttpUtil.getInstance().postAsyncFormData(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.deleteCallback(result.isResult(), course);
            }
        }, map);

    }

    @Override
    public void add(final ICourseContract.IPresenter presenter, final Course course) {
        String url = Constant.MANAGER_ADD_COURSE;
        HashMap<String, String> map = new HashMap<>();
        map.put("info", GsonUtil.gsonToJson(new CoursePost(course.getName(), course.getNo(), course.getSchool())));
//        Log.e(TAG, "add: "+map.get("info"));
        OkHttpUtil.getInstance().postAsyncFormData(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.addCallback(result.isResult(), course);
            }
        }, map);


    }

    private class CoursePost {

        CoursePost(String name, int no, String school) {
            this.name = name;
            this.no = no;
            this.school = school;
        }

        private String name;
        private int no;
        private String school;

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getSchool() {
            return school == null ? "" : school;
        }

        public void setSchool(String school) {
            this.school = school;
        }
    }
}
