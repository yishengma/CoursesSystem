package piratehat.coursessystem.bean;

/**
 *
 * Created by PirateHat on 2018/11/27.
 */
public class Teacher {
    private int mNo;
    private String mName;
    private String mSex;
    private String mSchool;
    private String mTel;

    public int getNo() {
        return mNo;
    }

    public void setNo(int no) {
        mNo = no;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String sex) {
        mSex = sex;
    }

    public String getSchool() {
        return mSchool;
    }

    public void setSchool(String school) {
        mSchool = school;
    }

    public String getTel() {
        return mTel;
    }

    public void setTel(String tel) {
        mTel = tel;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "mNo=" + mNo +
                ", mName='" + mName + '\'' +
                ", mSex='" + mSex + '\'' +
                ", mSchool='" + mSchool + '\'' +
                ", mTel='" + mTel + '\'' +
                '}';
    }
}
