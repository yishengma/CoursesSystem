package piratehat.coursessystem.dto;

import piratehat.coursessystem.bean.Teacher;

/**
 *
 * Created by PirateHat on 2018/11/27.
 */

public class TeacherDto implements Mapper<Teacher> {

    private String name;
    private int no;
    private String school;
    private String sex;
    private String tel;

    @Override
    public Teacher transform() {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setNo(no);
        teacher.setSchool(school);
        teacher.setTel(tel);
        return teacher;
    }


    public String getTel() {
        return tel == null ? "" : tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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
