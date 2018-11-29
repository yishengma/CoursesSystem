package piratehat.coursessystem.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.OutputKeys;

import okhttp3.Call;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ITeachContract;
import piratehat.coursessystem.contract.ITeacherContract;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class TeacherModel implements ITeacherContract.IModel {
//    private static final String TAG = "TeacherModel";
    @Override
    public void Update(final ITeacherContract.IPresenter presenter, final Teacher teacher) {
        String url = Constant.TEACHER_UPDATE;
        Map<String, String> map = new HashMap<>();
        map.put("", GsonUtil.gsonToJson(new PostTeacher(teacher)));
        OkHttpUtil.getInstance().postAsyncFormData(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: "+msg );
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.UpdateCallback(result.isResult(),teacher);
            }
        }, map);
    }

    class PostTeacher {
        private int no;
        private String name;
        private String sex;
        private String school;
        private String tel;

        public PostTeacher(Teacher teacher) {
            this.no = teacher.getNo();
            this.name = teacher.getName();
            sex = teacher.getSex();
            school = teacher.getSchool();
            tel = teacher.getTel();

        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSchool() {
            return school == null ? "" : school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getTel() {
            return tel == null ? "" : tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
