package piratehat.coursessystem.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.InfoAdapter;
import piratehat.coursessystem.base.BaseFragment;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.bean.Teacher;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ITeacherInfoContract;
import piratehat.coursessystem.presenter.TeacherInfoPresenter;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class TeacherInfoFragment extends BaseFragment implements ITeacherInfoContract.IView {


    @BindView(R.id.rv_info)
    RecyclerView mRvInfo;
    @BindView(R.id.btn_add)
    FloatingActionButton mBtnAdd;
    private List<Teacher> mTeachers;
    private InfoAdapter mAdapter;
    private TeacherInfoPresenter mPresenter;
    private int mPosition;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_teacher_info;
    }

    @Override
    protected void initData(Bundle bundle) {
        mTeachers = new ArrayList<>();
        mAdapter = new InfoAdapter(mTeachers, mActivity, Constant.TEACHER_TYPE);
        mPresenter = new TeacherInfoPresenter(this);

    }


    @Override
    protected void initView() {
        mRvInfo.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRvInfo.setAdapter(mAdapter);

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            mPresenter.getTeachers();
        }
    }

    @Override
    protected void initListener() {
        mAdapter.setListener(new InfoAdapter.OnClickListener() {
            @Override
            public void OnClick(Object o, int position) {
                mPosition = position;
            }

            @Override
            public void OnLongClick(Object o, int position) {
                mPosition = position;
                showNormalDialog((Teacher) o);
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });
    }

    @Override
    public void addCallback(boolean add, Teacher teacher) {
        if (add) {
            Toast.makeText(mActivity, "添加成功", Toast.LENGTH_SHORT).show();
            mTeachers.add(teacher);
            mAdapter.notifyDataSetChanged();
        }else {
            Toast.makeText(mActivity, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleteCallBack(boolean delete, String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
        if (delete) {
            mTeachers.remove(mPosition);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTeachers(List list) {
        mTeachers.clear();
        mTeachers.addAll(list);
        mAdapter.notifyDataSetChanged();
    }


    public static TeacherInfoFragment newInstance(Bundle bundle) {
        TeacherInfoFragment fragment = new TeacherInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TeacherInfoFragment newInstance() {
        return newInstance(null);
    }


    private void showNormalDialog(final Teacher teacher) {

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mActivity);
        normalDialog.setTitle("删除？");
        normalDialog.setMessage("确定删除编码为" + teacher.getNo() + "的教师信息？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delete(String.valueOf(teacher.getNo()));
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        normalDialog.show();
    }

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(mActivity);
        final View dialogView = LayoutInflater.from(mActivity)
                .inflate(R.layout.dialog_teacher_info, null);
        customizeDialog.setTitle("添加教师");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText no = dialogView.findViewById(R.id.et_no);

                        EditText name = dialogView.findViewById(R.id.et_name);

                        EditText tel = dialogView.findViewById(R.id.et_tel);

                        EditText sex = dialogView.findViewById(R.id.et_sex);

                        EditText school = dialogView.findViewById(R.id.et_school);

                        boolean duplicate = false;

                        for (Teacher s : mTeachers) {
                            if (s.getNo() == Integer.valueOf(no.getText().toString())) {
                                Toast.makeText(mActivity, "编号重复", Toast.LENGTH_SHORT).show();
                                duplicate = true;
                                break;
                            }
                        }

                        if (!duplicate && isNull(no, name, tel, sex, school)) {
                            Teacher teacher = new Teacher();
                            teacher.setTel(tel.getText().toString());
                            teacher.setName(name.getText().toString());
                            teacher.setSchool(school.getText().toString());
                            teacher.setSex(sex.getText().toString());
                            teacher.setNo(Integer.valueOf(no.getText().toString()));
                            mPresenter.add(teacher);
                        }

                    }

                });
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        customizeDialog.show();
    }

    private boolean isNull(EditText... editTexts) {

        for (EditText string : editTexts) {
            if (TextUtils.isEmpty(string.getText().toString())) {
                Toast.makeText(mActivity, "不能为空！", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
