package piratehat.coursessystem.ui;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import piratehat.coursessystem.R;
import piratehat.coursessystem.app.App;
import piratehat.coursessystem.base.BaseActivity;
import piratehat.coursessystem.constant.Constant;
import piratehat.coursessystem.contract.ILoginContract;
import piratehat.coursessystem.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements ILoginContract.IView {

    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;
    @BindView(R.id.rg_button)
    RadioGroup mRgButton;
    @BindView(R.id.btn_login)
    Button mBtnLogin;


    private LoginPresenter mPresenter;
    private String mType;

    @Override
    protected int setResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle bundle) {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initListener() {
//        mEtAccount.setText("123456");
//        mEtPassword.setText("123456");
                mEtAccount.setText("4650");
        mEtPassword.setText("马大生");
        mRgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbtn_student:
                        mEtAccount.setHint("学号");
                        mEtPassword.setHint("姓名");
                        App.Type = Constant.STUDENT;
                        break;
                    case R.id.rbtn_teacher:
                        mEtAccount.setHint("教师编号");
                        mEtPassword.setHint("姓名");
                        App.Type = Constant.TEACHER;
                        break;
                    case R.id.rbtn_manager:
                        mEtAccount.setHint("账号");
                        mEtPassword.setHint("密码");
                        App.Type = Constant.MANAGER;
                        break;
                    default:
                        break;
                }
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = mEtAccount.getText().toString();
                String password = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
                    mPresenter.login(App.Type, account, password);
                }
            }
        });


    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void login(Object o) {
        App.setUser(o);
        switch (App.Type) {
            case Constant.STUDENT:
                StudentActivity.actionStart(LoginActivity.this,StudentActivity.class);
                break;
            case Constant.TEACHER:
                TeacherActivity.actionStart(LoginActivity.this,TeacherActivity.class);
                break;
            default:
                ManagerActivity.actionStart(LoginActivity.this,ManagerActivity.class);
                break;

        }

    }
}
