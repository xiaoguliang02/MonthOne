package com.example.apple.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.apple.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.image_QQ)
    ImageView imageQQ;
    @BindView(R.id.ed_login_phone)
    EditText edLoginPhone;
    @BindView(R.id.ed_login_pwd)
    EditText edLoginPwd;
    @BindView(R.id.ck_login_remember)
    CheckBox ckLoginRemember;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    @BindView(R.id.btn_login_QQ)
    Button btnLoginQQ;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        edit = sp.edit();
        boolean flag = sp.getBoolean("flag", false);
        if (flag) {
            edLoginPhone.setText(sp.getString("phone",""));
            edLoginPwd.setText(sp.getString("pwd",""));
            ckLoginRemember.setChecked(flag);
        }
    }

    @OnClick({R.id.ck_login_remember, R.id.btn_login, R.id.btn_regist, R.id.btn_login_QQ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ck_login_remember:
                break;
            case R.id.btn_login:
                String phone = edLoginPhone.getText().toString();
                String pwd = edLoginPwd.getText().toString();
                if (pwd != null&&phone!=null) {
                    if (ckLoginRemember.isChecked()) {
                        edit.putString("phone",phone);
                        edit.putString("pwd",pwd);
                        edit.putBoolean("flag",ckLoginRemember.isChecked());
                    }else{
                        edit.putBoolean("flag",ckLoginRemember.isChecked());
                    }
                    edit.commit();
                    Intent intent = new Intent(LoginActivity.this, ShoppingActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.btn_regist:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login_QQ:
                break;
        }
    }
}
