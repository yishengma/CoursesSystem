package piratehat.coursessystem.ui.fragment;

import android.os.Bundle;

import piratehat.coursessystem.R;
import piratehat.coursessystem.base.BaseFragment;

/**
 *
 * Created by PirateHat on 2018/11/27.
 */

public class TeacherInfoFragment extends BaseFragment {

    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_teacher_info;
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

    public static TeacherInfoFragment newInstance(Bundle bundle) {
        TeacherInfoFragment fragment = new TeacherInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TeacherInfoFragment newInstance() {
        return newInstance(null);
    }
}
