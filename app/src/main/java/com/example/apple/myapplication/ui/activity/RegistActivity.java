package com.example.apple.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.apple.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistActivity extends AppCompatActivity {

    @BindView(R.id.image_QQ)
    ImageView imageQQ;
    @BindView(R.id.ed_regist_phone)
    EditText edRegistPhone;
    @BindView(R.id.ed_regist_pwd)
    EditText edRegistPwd;
    @BindView(R.id.ed_regist_pwd_agin)
    EditText edRegistPwdAgin;
    @BindView(R.id.btn_regist_return)
    Button btnRegistReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_regist_return)
    public void onViewClicked() {
        String phone = edRegistPhone.getText().toString();
        String pwd = edRegistPwd.getText().toString();
        String pwdagin = edRegistPwdAgin.getText().toString();
        if (pwdagin != null&&pwd!=null&&phone!=null) {
            Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
