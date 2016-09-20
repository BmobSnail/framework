package me.bugsrain.test1.ui.main;

import me.bugsrain.test1.api.Api;

/**
 * Created by quan on 16/9/19.
 */

public class MainPresenter extends MainContract.Presenter {

    @Override
    void getDoctorList(int page) {
        mRxManager.add(mModel.getDoctorList(page)
                .doOnSubscribe(() -> mView.onRequestStart())
                .subscribe(listEntity -> {
                    if(listEntity.getCode() == Api.Success){
                        mView.onGetDoctorSuccess(listEntity.getResult());
                                            }else{
                        mView.onRequestError(listEntity.getMsg());
                    }
                    mView.onRequestEnd();
                }, throwable -> {
                    throwable.printStackTrace();
                    mView.onInternetError();
                    mView.onRequestEnd();
                })
        );
    }
}
