package piratehat.coursessystem.dto;

import java.util.ArrayList;
import java.util.List;

import piratehat.coursessystem.bean.Course;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class CoursesDto implements Mapper<List<Course>> {
    private String msg;
    private boolean result;
    private List<Course_1> mCourse1s;


    @Override
    public List<Course> transform() {
        ArrayList<Course> list = new ArrayList<>();
        for (int i = 0, size = mCourse1s.size(); i < size; i++) {
            Course course = new Course();
            Course_1 course_1 = mCourse1s.get(i);
            course.setNo(course_1.getNo());
            course.setName(course_1.getName());
            course.setSchool(course_1.getSchool());
            list.add(course);


        }
        return list;
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

    public List<Course_1> getCourse1s() {
        return mCourse1s;
    }

    public void setCourse1s(List<Course_1> course1s) {
        mCourse1s = course1s;
    }

    private class Course_1 {

        Course_1(String name, int no, String school) {
            this.name = name;
            this.no = no;
            this.school = school;
        }

        private String name;
        private int no;
        private String school;

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
    }
}
