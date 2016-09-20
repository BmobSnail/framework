package chengxinet.chengxilibs.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import chengxinet.chengxilibs.global.BaseImplCompat;
import chengxinet.chengxilibs.helper.BaseImplCompatHelper;
import chengxinet.chengxilibs.utils.MyLog;


public abstract class BaseActivity extends AppCompatActivity implements
        BaseImplCompat, View.OnClickListener, AdapterView.OnItemClickListener {

    private long mExitTime;

    private boolean isExit = false;

    protected Fragment mCurrFragment;

    protected int mFrameId;

    private BaseImplCompatHelper mHelper;

//    public T mPresenter;
//
//    public E mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onViewCreate(savedInstanceState);

        mHelper = new BaseImplCompatHelper(this);

//        mPresenter = TUtil.getResult(this, 0);
//        mModel = TUtil.getResult(this, 1);
//        if (this instanceof BaseView) mPresenter.setVM(this, mModel);

        MyLog.i("LifeCycle", getClass().getSimpleName() + " is onCreate()");

    }

    protected abstract void onViewCreate(Bundle savedInstanceState);

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initData();
        initView();
        initListener();
        initLoad();
        MyLog.i("LifeCycle",getClass().getSimpleName() + " is onPostCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyLog.i("LifeCycle", getClass().getSimpleName() + " is onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.i("LifeCycle", getClass().getSimpleName() + " is onStop()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.i("LifeCycle", getClass().getSimpleName() + " is onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLog.i("LifeCycle", getClass().getSimpleName() + " is onPause()");

    }

    @Override
    public void openActivity(Class<? extends BaseActivity> toActivity) {
        mHelper.openActivity(toActivity, null);
    }

    @Override
    public void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        mHelper.openActivity(toActivity, parameter, -1);
    }

    @Override
    public void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter, int code) {
        mHelper.openActivity(toActivity, parameter, code);
    }

    @Override
    public void showLongToast(String pMsg) {
        mHelper.showLongToast(pMsg);
    }

    @Override
    public void showShortToast(String pMsg) {
        mHelper.showShortToast(pMsg);
    }

    @Override
    public void hideKeyboard(View v) {
        mHelper.hideKeyboard(v);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    showShortToast("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void finish() {
        super.finish();
    }

    protected void onDestroy() {
        Log.i("LifeCycle", getClass().getSimpleName() + " is onDestroy()");
        super.onDestroy();
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
    }

    @CallSuper
    @Override
    public void initData() {
        mHelper.initData();
    }

    @CallSuper
    @Override
    public void initView() {
        mHelper.initView();
    }

    @CallSuper
    @Override
    public void initListener() {
        mHelper.initListener();
    }

    @CallSuper
    @Override
    public void initLoad() {
        mHelper.initLoad();
    }

    protected void setFrameId(int id) {
        mFrameId = id;
    }

    public void setCurrFragment(Fragment mCurrFragment) {
        this.mCurrFragment = mCurrFragment;
    }


    @Override
    public <T extends View> T findViewByIdToView(int id) {
        return mHelper.findViewByIdToView(id);
    }


    @Override
    public void setFunctionView(View v, String content, int picId) {
        mHelper.setFunctionView(v, content, picId);
    }

    @Override
    public void setTitle(String title) {
        mHelper.setTitle(title);
    }

    @Override
    public TextView getCenterTxt() {
        return mHelper.getCenterTxt();
    }

    @Override
    public View getLeft() {
        return mHelper.getLeft();
    }

    @Override
    public View getRight() {
        return mHelper.getRight();
    }


    @Override
    public void hideActionBar(boolean b) {
        mHelper.hideActionBar(b);
    }

    @Override
    public View getActionBarBg() {
        return mHelper.getActionBarBg();
    }

    @Override
    public void setActionBarBgColor(int color) {
        mHelper.setActionBarBgColor(color);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    protected void toFragment(Fragment toFragment) {

        if (mCurrFragment == null) {
            showShortToast("mCurrFragment is null!");
            return;
        }

        if (toFragment == null) {
            showShortToast("toFragment is null!");
            return;
        }
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment temp : fragments) {
                if (!temp.isHidden())
                    getSupportFragmentManager()
                            .beginTransaction()

                            .hide(temp)
                            .commit();
            }
        }

        if (toFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(mCurrFragment)
                    .show(toFragment).commit();

        } else {
            getSupportFragmentManager().beginTransaction()
                    .hide(mCurrFragment)
                    .add(mFrameId, toFragment).show(toFragment)
                    .commit();
        }
        mCurrFragment = toFragment;
    }

    protected void openTransition(Boolean arg) {
        if (arg) {
            overridePendingTransition(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);
            // overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        }
    }

    @Override
    public Context getContext() {
        return mHelper.getContext();
    }
}
