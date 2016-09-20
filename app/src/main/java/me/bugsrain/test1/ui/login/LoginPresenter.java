package me.bugsrain.test1.ui.login;


import android.content.Context;

import chengxinet.chengxilibs.entity.Entity;
import me.bugsrain.test1.api.Api;
import me.bugsrain.test1.entity.User;
import me.bugsrain.test1.frame.rx.ProgressSubscriber;

/**
 * Created by baixiaokang on 16/4/29.
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login(Context context, String name, String pass) {


        mRxManager.add(mModel.login(context, name, pass)
                .doOnSubscribe(() -> mView.onRequestStart())
                .subscribe(new ProgressSubscriber<Entity<User>>(context){
                    @Override
                    public void onNext(Entity<User> entity) {
                        super.onNext(entity);
                        if (entity.getCode() == Api.Success) {
                            mView.onLoginSuccess(entity.getResult());
                        } else {
                            mView.onRequestError(entity.getMsg());
                        }
                        mView.onRequestEnd();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.onInternetError();
                        mView.onRequestEnd();
                    }
                }));

//                .subscribe(entity -> {
//                    if (entity.getCode() == Api.Success) {
//                        mView.onLoginSuccess(entity.getResult());
//                    } else {
//                        mView.onRequestError(entity.getMsg());
//                    }
//                    mView.onRequestEnd();
//                }, e -> {
//                    mView.onInternetError();
//                    mView.onRequestEnd();
//                }));


    }
}
