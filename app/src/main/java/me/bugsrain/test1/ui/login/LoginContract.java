package me.bugsrain.test1.ui.login;


import android.content.Context;

import chengxinet.chengxilibs.entity.Entity;
import me.bugsrain.test1.entity.User;
import me.bugsrain.test1.frame.BaseModel;
import me.bugsrain.test1.frame.BasePresenter;
import me.bugsrain.test1.frame.BaseView;
import rx.Observable;

/**
 * Created by baixiaokang on 16/4/29.
 */
public interface LoginContract {
    interface Model extends BaseModel {
        Observable<Entity<User>> login(Context context, String name, String pass);
    }

    interface View extends BaseView {
        void onLoginSuccess(User user);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void login(Context context, String name, String pass);
    }
}
