package piratehat.coursessystem.dto;

import java.util.ArrayList;
import java.util.List;

import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.constant.Constant;

/**
 *
 * Created by PirateHat on 2018/11/28.
 */

public class PostStudent {
    private String type;
    private List<Student_1> list;

    public PostStudent(Student student) {
          type = Constant.STUDENT;
          list = new ArrayList<>();
          list.add(new Student_1(student.getAge(),student.getName(),student.getNo(),student.getSchool(),student.getSex()));
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Student_1> getList() {
        return list;
    }

    public void setList(List<Student_1> list) {
        this.list = list;
    }

    class Student_1{
        private int age;
        private String name;
        private int no;
        private String school;
        private String sex;

        public Student_1(int age, String name, int no, String school, String sex) {
            this.age = age;
            this.name = name;
            this.no = no;
            this.school = school;
            this.sex = sex;
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
