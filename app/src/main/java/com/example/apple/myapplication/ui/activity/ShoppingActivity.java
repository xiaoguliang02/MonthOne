package com.example.apple.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.ui.fragment.MyFragment;
import com.example.apple.myapplication.ui.fragment.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppingActivity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.radio_shopping)
    RadioButton radioShopping;
    @BindView(R.id.radio_my)
    RadioButton radioMy;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_layout,new ShoppingFragment()).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio_shopping:
                        transaction.replace(R.id.frame_layout,new ShoppingFragment());
                        break;
                    case R.id.radio_my:
                        transaction.replace(R.id.frame_layout,new MyFragment());
                        break;
                }
                transaction.commit();
            }
        });
    }

}
