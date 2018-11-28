package piratehat.coursessystem.dto;

import java.util.ArrayList;
import java.util.List;

import piratehat.coursessystem.bean.Teacher;

/**
 * Created by PirateHat on 2018/11/28.
 */

public class TeacherDtos implements Mapper<List<Teacher>> {

    private boolean result;
    private String msg;
    private List<TeacherDto> list;


    @Override
    public List<Teacher> transform() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (int i = 0, size = list.size(); i < size; i++) {
            Teacher teacher = new Teacher();
            TeacherDto dto = list.get(i);
            teacher.setNo(dto.getNo());
            teacher.setTel(dto.getTel());
            teacher.setSchool(dto.getSchool());
            teacher.setName(dto.getName());
            teacher.setSex(dto.getSex());

            teachers.add(teacher);


        }
        return teachers;
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

    public List<TeacherDto> getList() {
        return list;
    }

    public void setList(List<TeacherDto> list) {
        this.list = list;
    }
}
