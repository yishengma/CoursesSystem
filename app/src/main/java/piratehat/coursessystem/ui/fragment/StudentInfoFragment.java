package piratehat.coursessystem.ui.fragment;

import android.os.Bundle;

import java.util.ArrayList;

import piratehat.coursessystem.R;
import piratehat.coursessystem.base.BaseFragment;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class StudentInfoFragment extends BaseFragment {


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_student_info;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    public static StudentInfoFragment newInstance(Bundle bundle) {
        StudentInfoFragment fragment = new StudentInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static StudentInfoFragment newInstance() {
       return newInstance(null);
    }
}
