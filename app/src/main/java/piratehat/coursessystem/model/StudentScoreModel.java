package piratehat.coursessystem.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.IStudentScoreContract;
import piratehat.coursessystem.dto.ResultDto;
import piratehat.coursessystem.dto.StudentDots;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

/**
 * Created by PirateHat on 2018/11/29.
 */

public class StudentScoreModel implements IStudentScoreContract.IModel {
    private static final String TAG = "StudentScoreModel";

    @Override
    public void getStudents(final IStudentScoreContract.IPresenter presenter, int tno, int cno) {
        String url = Constant.TEACHER_STUDENT + "cno=" + cno + "&tno=" + tno;
//        Log.e(TAG, "getStudents: "+url );
        OkHttpUtil.getInstance().getAsync(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: "+msg);
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
    public void update(final IStudentScoreContract.IPresenter presenter, List<Student> list, int cno) {
        String url = Constant.TEACHER_SCORE;
        Map<String, String> map = new HashMap<>();
        map.put("info", GsonUtil.gsonToJson(new PostScores(cno,list)));
        OkHttpUtil.getInstance().postAsyncFormData(url, new OkHttpResultCallback() {
            @Override
            public void onError(Call call, Exception e) {
                presenter.showError(e.getMessage());
            }

            @Override
            public void onResponse(String msg) {
//                Log.e(TAG, "onResponse: " + msg);
//                Log.e(TAG, "onResponse: "+msg );
                Result result = GsonUtil.gsonToBean(msg, ResultDto.class).transform();
                if (result.isResult()){
                    presenter.setScoresCallBack();
                }
            }
        }, map);
    }

    class PostScores {
        private int type;
        private List<Score> list;


        public PostScores(int type, List<Student> students) {
            this.type = type;
            list = new ArrayList<>();
            for (int i = 0, size = students.size(); i < size; i++) {
                Student student = students.get(i);
                Score score = new Score();
                score.setScore(student.getScore());
                score.setSno(student.getNo());
                list.add(score);
            }

        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<Score> getList() {
            return list;
        }

        public void setList(List<Score> list) {
            this.list = list;
        }

        class Score {
            private int sno;
            private int score;

            public int getSno() {
                return sno;
            }

            public void setSno(int sno) {
                this.sno = sno;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }
        }
    }
}
