package piratehat.coursessystem.app;

import android.app.Application;


import piratehat.coursessystem.bean.Manager;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;

/**
 *
 * Created by PirateHat on 2018/11/27.
 */

public class App extends Application {
    public static String Type;
    private static Student mStudent;
    private static Teacher mTeacher;

    @Override
    public void onCreate() {
        super.onCreate();
        Type = Constant.STUDENT;
    }


    public static <T> T getUser() {
        switch (Type) {
            case Constant.STUDENT:
                return (T) mStudent;

            case Constant.TEACHER:
                return (T) mTeacher;


        }
        return null;
    }

    public static <T> void setUser(T t) {
        switch (Type) {
            case Constant.STUDENT:
                mStudent = (Student) t;
                break;
            case Constant.TEACHER:
                mTeacher = (Teacher) t;
                break;
        }
    }

}
