package piratehat.coursessystem.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import piratehat.coursessystem.R;
import piratehat.coursessystem.adapter.InfoAdapter;
import piratehat.coursessystem.base.BaseFragment;
import piratehat.coursessystem.bean.Student;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.IStudentInfoContract;
import piratehat.coursessystem.dto.StudentDots;
import piratehat.coursessystem.dto.TeacherDtos;
import piratehat.coursessystem.presenter.StudentInfoPresenter;
import piratehat.coursessystem.utils.GsonUtil;
import piratehat.coursessystem.utils.OkHttpResultCallback;
import piratehat.coursessystem.utils.OkHttpUtil;

import static android.support.v4.util.PatternsCompat.IP_ADDRESS;

/**
 * Created by PirateHat on 2018/11/27.
 */

public class StudentInfoFragment extends BaseFragment implements IStudentInfoContract.IView {


    @BindView(R.id.rv_info)
    RecyclerView mRvInfo;
    @BindView(R.id.btn_add)
    FloatingActionButton mBtnAdd;

    private List<Student> mStudents;
    private InfoAdapter mAdapter;
    private StudentInfoPresenter mPresenter;
    private int mPosition;

    @BindView(R.id.btn_search)
    FloatingActionButton mBtnSearch;


    @Override
    protected int setLayoutResId() {
        return R.layout.fragment_student_info;
    }

    @Override
    protected void initData(Bundle bundle) {
        mStudents = new ArrayList<>();
        mAdapter = new InfoAdapter(mStudents, mActivity, Constant.STUDENT_TYPE);
        mPresenter = new StudentInfoPresenter(this);


    }

    @Override
    protected void initView() {
        mRvInfo.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mRvInfo.setAdapter(mAdapter);

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            mPresenter.getStudents();
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
                showNormalDialog((Student) o);

            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchDialog();
            }
        });
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setStudents(List list) {
        mStudents.clear();
        mStudents.addAll(list);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void addCallback(boolean add, Student student) {

        if (add) {
            Toast.makeText(mActivity, "添加成功", Toast.LENGTH_SHORT).show();
            mStudents.add(student);
            mAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(mActivity, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void deleteCallBack(boolean delete, String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
        if (delete) {
            mStudents.remove(mPosition);
            mAdapter.notifyDataSetChanged();
        }
    }

    public static StudentInfoFragment newInstance(Bundle bundle) {
        StudentInfoFragment fragment = new StudentInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static StudentInfoFragment newInstance() {
        return newInstance(null);
    }


    private void showNormalDialog(final Student student) {

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mActivity);
        normalDialog.setTitle("删除？");
        normalDialog.setMessage("确定删除学号为" + student.getNo() + "的学生？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delete(String.valueOf(student.getNo()));
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

    private void showSearchDialog() {

        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(mActivity);
        final View dialogView = LayoutInflater.from(mActivity)
                .inflate(R.layout.dialog_search, null);
        customizeDialog.setTitle("搜索");

        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText no = dialogView.findViewById(R.id.et_str);
                        if (!TextUtils.isEmpty(no.getText().toString())) {
                            mPresenter.search(no.getText().toString());
                            dialog.dismiss();
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

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(mActivity);
        final View dialogView = LayoutInflater.from(mActivity)
                .inflate(R.layout.dialog_student_info, null);
        customizeDialog.setTitle("添加学生");

        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText no = dialogView.findViewById(R.id.et_no);

                        EditText name = dialogView.findViewById(R.id.et_name);

                        EditText age = dialogView.findViewById(R.id.et_age);

                        EditText sex = dialogView.findViewById(R.id.et_sex);

                        EditText school = dialogView.findViewById(R.id.et_school);

                        boolean duplicate = false;
                        for (Student s : mStudents) {
                            if (s.getNo() == Integer.valueOf(no.getText().toString())) {
                                Toast.makeText(mActivity, "学号重复", Toast.LENGTH_SHORT).show();
                                duplicate = true;
                                break;
                            }
                        }

                        if (!duplicate && isNull(no, name, age, sex, school)) {
                            Student student = new Student();
                            student.setAge(Integer.valueOf(age.getText().toString()));
                            student.setName(name.getText().toString());
                            student.setSchool(school.getText().toString());
                            student.setSex(sex.getText().toString());
                            student.setNo(Integer.valueOf(no.getText().toString()));
                            mPresenter.add(student);
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

    private boolean isNull(String... strings) {

        for (String string : strings) {
            if (TextUtils.isEmpty(string)) {
                return false;
            }
        }
        return true;
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
