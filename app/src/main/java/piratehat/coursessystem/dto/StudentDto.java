package piratehat.coursessystem.dto;

import piratehat.coursessystem.bean.Student;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class StudentDto implements Mapper<Student> {

     private int age;
     private String name;
     private int no;
     private String school;
     private String sex;
     private int score;

    @Override
    public Student transform() {
        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        student.setNo(no);
        student.setSchool(school);
        student.setScore(score);
        student.setSex(sex);
        return student;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
