package piratehat.coursessystem.bean;

/**
 * Created by PirateHat on 2018/11/27.
 */
public class Result<T> {

    private boolean mResult;
    private String mMsg;
    private Teacher mTeacher;
    private Student mStudent;
    private String mType;

    public String getType() {
        return mType == null ? "" : mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public boolean isResult() {
        return mResult;
    }

    public void setResult(boolean result) {
        mResult = result;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }


    public Teacher getTeacher() {
        return mTeacher;
    }

    public void setTeacher(Teacher teacher) {
        mTeacher = teacher;
    }

    public Student getStudent() {
        return mStudent;
    }

    public void setStudent(Student student) {
        mStudent = student;

    }

    @Override
    public String toString() {
        return "Result{" +
                "mResult=" + mResult +
                ", mMsg='" + mMsg + '\'' +
                ", mTeacher=" + mTeacher +
                ", mStudent=" + mStudent +
                '}';
    }
}
