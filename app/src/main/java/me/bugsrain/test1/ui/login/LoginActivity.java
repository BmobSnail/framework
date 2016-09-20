package me.bugsrain.test1.ui.login;

import android.os.Bundle;
import android.view.MotionEvent;

import me.bugsrain.test1.R;
import me.bugsrain.test1.entity.User;
import me.bugsrain.test1.frame.base.BaseFrameActivity;
import me.bugsrain.test1.ui.main.MainActivity;

/**
 * Created by quan on 16/9/1.
 */

public class LoginActivity extends BaseFrameActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        setExit(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initListener() {
        super.initListener();

    }

    @Override
    public void initLoad() {
        super.initLoad();
    }

    @Override
    public void onLoginSuccess(User user) {
        openActivity(MainActivity.class);
    }


    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                mPresenter.login(LoginActivity.this, "13425689624", "111111");
                break;
        }
        return super.onTouchEvent(event);
    }
}
