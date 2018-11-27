package piratehat.coursessystem.dto;

import piratehat.coursessystem.bean.Result;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.utils.GsonUtil;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class ResultDto implements Mapper<Result> {

    private String msg;
    private boolean result;
    private StudentDto student;
    private TeacherDto teacher;
    private String type;
    @Override
    public Result transform() {
        Result result = new Result();
        result.setMsg(msg);
        result.setResult(this.result);
        result.setStudent(student!=null?student.transform():null);
        result.setTeacher(teacher!=null?teacher.transform():null);
        result.setType(type);
        return result;

    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
