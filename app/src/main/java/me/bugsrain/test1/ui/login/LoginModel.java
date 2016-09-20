package me.bugsrain.test1.ui.login;


import android.content.Context;

import chengxinet.chengxilibs.entity.Entity;
import me.bugsrain.test1.api.Api;
import me.bugsrain.test1.entity.User;
import me.bugsrain.test1.frame.rx.RxSchedulers;
import rx.Observable;

/**
 * Created by baixiaokang on 16/5/2.
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public Observable<Entity<User>> login(Context context, String name, String pass) {
        return Api.getInstance().getService()
                .login(name, pass)
                .compose(RxSchedulers.io_main());
//                .compose(RxSchedulers.handleDialog(context));
    }

}
