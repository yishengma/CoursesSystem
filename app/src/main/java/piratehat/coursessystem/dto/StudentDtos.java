package piratehat.coursessystem.dto;

import java.util.ArrayList;
import java.util.List;

import piratehat.coursessystem.bean.Student;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class StudentDtos implements Mapper<List<Student>> {
    private boolean result;
    private String msg;
    private List<StudentDto> list;


    @Override
    public List<Student> transform() {
        List<Student> students = new ArrayList<>();
        for (int i = 0, size = list.size(); i < size; i++) {
            Student student = new Student();
            StudentDto dto = list.get(i);
            student.setNo(dto.getNo());
            student.setSex(dto.getSex());
            student.setScore(null);
            student.setSchool(dto.getSchool());
            student.setName(dto.getName());
            student.setAge(dto.getAge());
            students.add(student);
        }
        return students;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<StudentDto> getList() {
        return list;
    }

    public void setList(List<StudentDto> list) {
        this.list = list;
    }


}
