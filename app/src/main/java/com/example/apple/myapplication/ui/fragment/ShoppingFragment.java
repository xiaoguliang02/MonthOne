package com.example.apple.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apple.myapplication.R;
import com.example.apple.myapplication.data.bean.ShoppingBean;
import com.example.apple.myapplication.di.contract.IShoppingContract;
import com.example.apple.myapplication.di.presenter.ShoppingPresenter;
import com.example.apple.myapplication.ui.adapter.BussinessAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingFragment extends Fragment implements IShoppingContract.IShoppingView {

    @BindView(R.id.shopping_recycle)
    RecyclerView shoppingRecycle;
    @BindView(R.id.ckAll)
    CheckBox ckAll;
    @BindView(R.id.text_totalprice)
    TextView textTotalprice;
    Unbinder unbinder;
    private View view;
    private List<ShoppingBean.DataBean> data;
    private IShoppingContract.IShoppingPresenter shoppingPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shopping_layout, null);
        unbinder = ButterKnife.bind(this, view);
        shoppingPresenter = new ShoppingPresenter();
        shoppingPresenter.attachView(this);
        shoppingPresenter.requestData();
        ckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setBusinessChecked(ckAll.isChecked());
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        data.get(i).getList().get(j).setGoodsChecked(ckAll.isChecked());
                    }
                }
                calculatorTotalCount();
            }
        });
        return view;
    }

    @Override
    public void showData(String json) {
        Gson gson = new Gson();
        ShoppingBean shoppingBean = gson.fromJson(json, ShoppingBean.class);
        data = shoppingBean.getData();
        Toast.makeText(getActivity(), "data:" + data, Toast.LENGTH_SHORT).show();
        BussinessAdapter bussinessAdapter = new BussinessAdapter(R.layout.bussiness_layout, data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        shoppingRecycle.setLayoutManager(linearLayoutManager);
        shoppingRecycle.setAdapter(bussinessAdapter);
        bussinessAdapter.setOnBussinessClickLister(new BussinessAdapter.OnBussinessClickLister() {
            @Override
            public void onBussinessCallBack() {
                boolean result=true;
                for (int i = 0; i < data.size(); i++) {
                    result=result& data.get(i).getBusinessChecked();
                    for (int j = 0; j < data.get(i).getList().size(); j++) {
                        result=result& data.get(i).getList().get(j).getGoodsChecked();
                    }
                }
                ckAll.setChecked(result);
                notify();
                calculatorTotalCount();
            }

        });
    }
    private void calculatorTotalCount() {
        double totalCount=0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).getList().size(); j++) {
                double num = data.get(i).getList().get(j).getNum();
                double price = data.get(i).getList().get(j).getPrice();
                double goods = num * price;
                totalCount=totalCount+goods;
            }
        }
        textTotalprice.setText("总价为:"+totalCount);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        shoppingPresenter.detachView(this);
    }
}
