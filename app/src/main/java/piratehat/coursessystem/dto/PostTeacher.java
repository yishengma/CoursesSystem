package piratehat.coursessystem.dto;

import java.util.ArrayList;
import java.util.List;

import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class PostTeacher {

    private String type;
    private List<Teacher_1> list;

    public PostTeacher(Teacher teacher) {
        type = Constant.TEACHER;
        list = new ArrayList<>();
        list.add(new Teacher_1(teacher.getName(), teacher.getNo(), teacher.getSchool(), teacher.getSex(), teacher.getTel()));
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Teacher_1> getList() {
        return list;
    }

    public void setList(List<Teacher_1> list) {
        this.list = list;
    }

    class Teacher_1 {


        private String name;
        private int no;
        private String school;
        private String sex;
        private String tel;

        public Teacher_1(String name, int no, String school, String sex, String tel) {
            this.name = name;
            this.no = no;
            this.school = school;
            this.sex = sex;
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

        public String getTel() {
            return tel == null ? "" : tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
