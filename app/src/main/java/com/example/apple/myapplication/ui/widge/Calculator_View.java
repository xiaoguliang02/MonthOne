package com.example.apple.myapplication.ui.widge;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apple.myapplication.R;

public class Calculator_View extends LinearLayout implements View.OnClickListener {

    private final Button btn_add;
    private final Button btn_reduce;
    private final TextView text_num;

    public Calculator_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.calculator_layout, null);
        btn_add = inflate.findViewById(R.id.btn_add);
        btn_reduce = inflate.findViewById(R.id.btn_reduce);
        text_num = inflate.findViewById(R.id.text_num);
        btn_add.setOnClickListener(this);
        btn_reduce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String num = text_num.getText().toString();
        int number = Integer.parseInt(num);
        switch (v.getId()){
            case R.id.btn_add:
                number=number+1;
                onCalculatorClickLister.addCallBack(number);
                break;
            case R.id.btn_reduce:
                number=number-1;
                if (number<0) {
                    number=0;
                    onCalculatorClickLister.reduceCallBack(number);
                }
                onCalculatorClickLister.reduceCallBack(number);
                break;
        }
    }
    OnCalculatorClickLister onCalculatorClickLister;

    public void setOnCalculatorClickLister(OnCalculatorClickLister onCalculatorClickLister) {
        this.onCalculatorClickLister = onCalculatorClickLister;
    }

    public interface OnCalculatorClickLister{
        public void addCallBack(int s);
        public void reduceCallBack(int s);
    }
}
