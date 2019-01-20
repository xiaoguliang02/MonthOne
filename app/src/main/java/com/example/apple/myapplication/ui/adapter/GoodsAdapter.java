package com.example.apple.myapplication.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.myapplication.R;
import com.example.apple.myapplication.data.bean.ShoppingBean;
import com.example.apple.myapplication.ui.widge.Calculator_View;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ShoppingBean.DataBean.ListBean, BaseViewHolder> {
    OnGoodsClickLister onGoodsClickLister;

    public void setOnGoodsClickLister(OnGoodsClickLister onGoodsClickLister) {
        this.onGoodsClickLister = onGoodsClickLister;
    }

    public interface OnGoodsClickLister {
        public void onGoodsCallBack();
    }

    public GoodsAdapter(int layoutResId, @Nullable List<ShoppingBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingBean.DataBean.ListBean item) {
        Calculator_View calculate = helper.getView(R.id.calculator);
        /*TextView text_goods_num = calculate.findViewById(R.id.text_num);
        text_goods_num.setText(item.getNum() + "");*/
        helper.setText(R.id.goods_title, item.getTitle());
        helper.setText(R.id.goods_price, "¥" + item.getPrice());
        String images = item.getImages();
        String[] imagesString = images.split("\\|");
        Glide.with(mContext).load(imagesString[0]).into((ImageView) helper.getView(R.id.goods_image));
        final CheckBox ck_goods_one = helper.getView(R.id.ck_goods);
        ck_goods_one.setOnClickListener(null);
        ck_goods_one.setChecked(item.getGoodsChecked());
        ck_goods_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setGoodsChecked(isChecked);
                onGoodsClickLister.onGoodsCallBack();
            }
        });
        calculate.setOnCalculatorClickLister(new Calculator_View.OnCalculatorClickLister() {
            @Override
            public void addCallBack(int s) {
                item.setNum(s);
                onGoodsClickLister.onGoodsCallBack();
            }

            @Override
            public void reduceCallBack(int s) {
                item.setNum(s);
                onGoodsClickLister.onGoodsCallBack();
            }
        });
    }


}
