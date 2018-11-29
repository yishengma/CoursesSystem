package piratehat.coursessystem.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.OutputKeys;

import okhttp3.Call;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.contract.IStudentContract;
import piratehat.coursessystem.contract.IStudentScoreContract;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

import static piratehat.coursessystem.constant.Constant.STUDENT_UPDATE;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class StudentModel implements IStudentContract.IModel{
    private static final String TAG = "StudentModel";
    @Override
    public void Update(final IStudentContract.IPresenter presenter, final Student student) {
        String url = STUDENT_UPDATE;
        Map<String,String> map = new HashMap<>();
        map.put("info", GsonUtil.gsonToJson(new PostStudent(student)));
        OkHttpUtil.getInstance().postAsyncFormData(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: "+msg );
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                presenter.UpdateCallback(result.isResult(),student);
            }
        },map);
    }

    class PostStudent{
        private int age;
        private String name;
        private int no;
        private String school;
        private String sex;

        public PostStudent(Student student) {
            no = student.getNo();
            name = student.getName();
            age = student.getAge();
            school = student.getSchool();
            sex = student.getSex();
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

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

        public String getSex() {
            return sex == null ? "" : sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
