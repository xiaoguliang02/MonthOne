package com.example.apple.myapplication.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.apple.myapplication.R;
import com.example.apple.myapplication.data.bean.ShoppingBean;

import java.util.List;

public class BussinessAdapter extends BaseQuickAdapter<ShoppingBean.DataBean,BaseViewHolder> {
    OnBussinessClickLister onBussinessClickLister;
    private List<ShoppingBean.DataBean.ListBean> bussinesslist;
    private RecyclerView bussiness_recycle;

    public void setOnBussinessClickLister(OnBussinessClickLister onBussinessClickLister) {
        this.onBussinessClickLister = onBussinessClickLister;
    }

    public interface OnBussinessClickLister{
        public void onBussinessCallBack();
    }
    public BussinessAdapter(int layoutResId, @Nullable List<ShoppingBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingBean.DataBean item) {
        bussiness_recycle = helper.getView(R.id.goods_recycle);
        helper.setText(R.id.text_bussiness_name,item.getSellerName());
        bussinesslist = item.getList();
        final CheckBox ck_bussiness = helper.getView(R.id.ck_bussiness);
        ck_bussiness.setOnCheckedChangeListener(null);
        ck_bussiness.setChecked(item.getBusinessChecked());
        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.goods_recycle_layout, bussinesslist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        bussiness_recycle.setLayoutManager(linearLayoutManager);
        bussiness_recycle.setAdapter(goodsAdapter);
        goodsAdapter.setOnGoodsClickLister(new GoodsAdapter.OnGoodsClickLister() {
            @Override
            public void onGoodsCallBack() {
                boolean result=true;
                for (int i = 0; i < bussinesslist.size(); i++) {
                    result=result& bussinesslist.get(i).getGoodsChecked();
                }
                item.setBusinessChecked(result);
                ck_bussiness.setChecked(result);
                notifyDataSetChanged();
                onBussinessClickLister.onBussinessCallBack();
            }
        });
        ck_bussiness.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < bussinesslist.size(); i++) {
                    bussinesslist.get(i).setGoodsChecked(ck_bussiness.isChecked());
                }
                ck_bussiness.setChecked(ck_bussiness.isChecked());
                goodsAdapter.notifyDataSetChanged();
                onBussinessClickLister.onBussinessCallBack();
            }
        });
    }
}
