package com.example.apple.myapplication.di.contract;

public interface IShoppingContract {
    public interface IShoppingView{
        public void showData(String json);
    }
    public interface IShoppingPresenter<IShoppingView>{
        public void attachView(IShoppingView iShoppingView);
        public void detachView(IShoppingView iShoppingView);
        public void requestData();
    }
    public interface IShoppingModel{
        public void constantResponseData(MyCallBack myCallBack);
        public interface MyCallBack{
            public void responseData(String json);
        }
    }
}
