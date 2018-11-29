package piratehat.coursessystem.constant;

public class Constant {

    public static final String MANAGER = "manager";
    public static final String STUDENT = "student";
    public static final String TEACHER = "teacher";
    public static final String COURSE = "course";

    public static final int STUDENT_TYPE = 1;
    public static final int TEACHER_TYPE = 2;

    public static final int SELECTED = 0;
    public static final int UNSELECTED = 1;


    public static final int CONNECT_TIMEOUT = 6;
    public static final int READ_TIMEOUT = 100;
    public static final int WRITE_TIMEOUT = 60;



    private static final String IP_ADDRESS = "http://10.21.20.27:8080";
    public static final String LOGIN = IP_ADDRESS + "/web/login";

    //管理员
    public static final String MANAGER_QUERY = IP_ADDRESS + "/web/manager/query?type=";
    public static final String MANAGER_ADD = IP_ADDRESS + "/web/manager/add";
    public static final String MANAGER_DELETE = IP_ADDRESS + "/web/manager/delete?";
    public static final String MANAGER_DELETE_COURSE = IP_ADDRESS+"/web/manager/course/delete";
    public static final String MANAGER_ADD_COURSE = IP_ADDRESS+"/web/manager/course/add";



    //学生
    public static final String STUDENT_COURSE = IP_ADDRESS + "/web/student/course?";
    public static final String STUDENT_UNSELECTED = IP_ADDRESS+"/web/student/unselect?";
    public static final String STUDENT_SELECT = IP_ADDRESS+"/web/student/select?";
    public static final String STUDENT_UPDATE = IP_ADDRESS+"/web/student/update";

    //教师
    public static final String TEACHER_TEACH = IP_ADDRESS+"/web/teacher/teach";
    public static final String TEACHER_NO_TEACH = IP_ADDRESS +"/web/teacher/no_teach";
    public static final String TEACHER_SELECT  =IP_ADDRESS +"/web/teacher/course?";
    public static final String TEACHER_STUDENT = IP_ADDRESS+"/web/teacher/query?";
    public static final String TEACHER_SCORE = IP_ADDRESS+"/web/teacher/score";
    public static final String TEACHER_UPDATE = IP_ADDRESS+"/web/teacher/update";





}
