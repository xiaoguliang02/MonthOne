package com.example.apple.myapplication.di.model;

import com.example.apple.myapplication.data.constant.Constant;
import com.example.apple.myapplication.di.contract.IShoppingContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class ShoppingModel implements IShoppingContract.IShoppingModel {
    @Override
    public void constantResponseData(final MyCallBack myCallBack) {
        OkGo.<String>get(Constant.SHOPPING_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String json = response.body().toString();
                myCallBack.responseData(json);
            }
        });
    }
}
