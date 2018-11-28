package piratehat.coursessystem.dto;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import piratehat.coursessystem.bean.Course;
import piratehat.coursessystem.bean.Teacher;

/**
 * Created by PirateHat on 2018/11/28.
 */

/*
{
	"list": [{
		"course": {
			"name": "篮球",
			"no": 2,
			"school": "广东工业大学"
		},
		"teacher": {
			"name": "张铭",
			"no": 0,
			"school": "广东工业大学",
			"sex": "男",
			"tel": "13178910461"
		}
	}],
	"msg": "成功！",
	"result": true,
	"type": "student"
}
 */
public class SelectCoursesDto implements Mapper<List<Course>> {

    private List<CourseDto> list;
    private String msg;
    private boolean result;


    @Override
    public List<Course> transform() {
        List<Course> courses = new ArrayList<>();

        for (int i = 0, size = list.size(); i < size; i++) {
            CourseDto dto = list.get(i);
            Course course = new Course();
            course.setNo(dto.getCourse().getNo());
            course.setName(dto.getCourse().getName());
            course.setSchool(dto.getCourse().getSchool());
            course.setScore(dto.getScore());
            Teacher teacher = new Teacher();
            teacher.setTel(dto.getTeacher().getTel());
            teacher.setName(dto.getTeacher().getName());
            teacher.setSchool(dto.getTeacher().getSchool());
            teacher.setSex(dto.getTeacher().getSex());

            course.setTeacher(teacher);
            courses.add(course);

        }
        Log.e("TAG", "transform: "+courses );
        return courses;
    }

    public List<CourseDto> getList() {
        return list;
    }

    public void setList(List<CourseDto> list) {
        this.list = list;
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

    class CourseDto {
        private Course_1 course;
        private Teacher_1 teacher;
        private Integer score;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Course_1 getCourse() {
            return course;
        }

        public void setCourse(Course_1 course) {
            this.course = course;
        }

        public Teacher_1 getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher_1 teacher) {
            this.teacher = teacher;
        }
    }

    class Teacher_1 {
        private String name;
        private int no;
        private String school;
        private String sex;
        private String tel;

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

   public class Course_1 {



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
