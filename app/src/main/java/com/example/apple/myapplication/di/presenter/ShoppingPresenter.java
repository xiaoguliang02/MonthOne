package com.example.apple.myapplication.di.presenter;

import com.example.apple.myapplication.di.contract.IShoppingContract;
import com.example.apple.myapplication.di.model.ShoppingModel;

import java.lang.ref.SoftReference;

public class ShoppingPresenter implements IShoppingContract.IShoppingPresenter<IShoppingContract.IShoppingView> {
    IShoppingContract.IShoppingView iShoppingView;
    private IShoppingContract.IShoppingModel shoppingModel;
    private SoftReference<IShoppingContract.IShoppingView> reference;

    @Override
    public void attachView(IShoppingContract.IShoppingView iShoppingView) {
        this.iShoppingView=iShoppingView;
        shoppingModel = new ShoppingModel();
        reference = new SoftReference<>(iShoppingView);
    }

    @Override
    public void detachView(IShoppingContract.IShoppingView iShoppingView) {
        reference.clear();
    }

    @Override
    public void requestData() {
    shoppingModel.constantResponseData(new IShoppingContract.IShoppingModel.MyCallBack() {
    @Override
    public void responseData(String json) {
        iShoppingView.showData(json);
    }
});
    }
}
