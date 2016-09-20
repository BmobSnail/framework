package chengxinet.chengxilibs.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import chengxinet.chengxilibs.R;
import chengxinet.chengxilibs.activity.BaseActivity;
import chengxinet.chengxilibs.fragment.BaseFragment;
import chengxinet.chengxilibs.global.BaseImplCompat;
import chengxinet.chengxilibs.global.ChengXiApplication;

/**
 * Created by quan on 16/8/25.
 */

public class BaseImplCompatHelper implements BaseImplCompat {

    private enum Mode {
        ACTIVITY,
        FRAGMENT
    }

    private Mode mode;

    private Context mContext;

    private BaseActivity mActivity;

    private BaseFragment mFragment;

    private TextView mCenterTxt;

    private View mLeftTxt, mRightTxt, mActionBarBg;

    private RelativeLayout mActionBarContain;

    public BaseImplCompatHelper(BaseImplCompat compat) {
        if (compat instanceof Fragment) {
            mFragment = (BaseFragment) compat;
            mContext = mFragment.getContext();
            mode = Mode.FRAGMENT;
        } else {
            mActivity = (BaseActivity) compat;
            mContext = mActivity;
            mode = Mode.ACTIVITY;
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mActionBarContain = findViewByIdToView(R.id.action_bar_layout);
        mCenterTxt = findViewByIdToView(R.id.actionBar_center_txt);
        mLeftTxt = findViewByIdToView(R.id.actionBar_center_txt);
        mRightTxt = findViewByIdToView(R.id.actionBar_center_txt);
        mActionBarBg = findViewByIdToView(R.id.actionBar_bg);

        setActionBarBgColor(-1);
    }

    @Override
    public void initListener() {
        if (mActionBarContain != null) {
            View.OnClickListener listener;
            if (mode == Mode.ACTIVITY) {
                listener = mActivity;
            } else {
                listener = mFragment;
            }
            mLeftTxt.setOnClickListener(listener);
            mRightTxt.setOnClickListener(listener);

        }
    }

    @Override
    public void initLoad() {

    }

    @Override
    public void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    @Override
    public void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        openActivity(toActivity, parameter, -1);
    }

    @Override
    public void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter, int code) {
        Intent intent = new Intent(mContext, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }

        if (code == -1) {
            mContext.startActivity(intent);
        } else {
            if (mode == Mode.ACTIVITY) {
                mActivity.startActivityForResult(intent, code);
            } else {
                mFragment.startActivityForResult(intent, code);
            }
        }
    }

    @Override
    public void showLongToast(String pMsg) {
        Toast.makeText(mContext, pMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShortToast(String pMsg) {
        Toast toast = Toast.makeText(mContext, pMsg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) mContext.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    @Override
    public <T extends View> T findViewByIdToView(int id) {
        T t;
        if (mode == Mode.ACTIVITY) {
            t = (T) mActivity.findViewById(id);
        } else {
            t = (T) mFragment.getmContentView().findViewById(id);
        }
        return t;
    }

    @Override
    public void setTitle(String title) {
        if (mCenterTxt != null) {
            mCenterTxt.setText(title);
            mCenterTxt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setFunctionView(View v, String content, int picId) {
        if (v != null && v instanceof TextView) {
            TextView target = (TextView) v;
            if (TextUtils.isEmpty(content)) {
                target.setText("");
            } else {
                v.setVisibility(View.VISIBLE);
                target.setText(content);
            }

            if (picId == -1) {
                target.setCompoundDrawables(null, null, null, null);
            } else {
                v.setVisibility(View.VISIBLE);
                if (v.equals(mLeftTxt)) {
                    target.setCompoundDrawablesWithIntrinsicBounds(picId, 0, 0, 0);
                } else if (v.equals(mRightTxt)) {
                    target.setCompoundDrawablesWithIntrinsicBounds(0, 0, picId, 0);
                }

            }
        }
    }

    @Override
    public TextView getCenterTxt() {
        return mCenterTxt;
    }

    @Override
    public View getLeft() {
        return mLeftTxt;
    }

    @Override
    public View getRight() {
        return mRightTxt;
    }

    @Override
    public View getActionBarBg() {
        return mActionBarBg;
    }

    @Override
    public void hideActionBar(boolean b) {
        if (mActionBarContain != null) {
            if (b) {
                mActionBarContain.setVisibility(View.GONE);
            } else {
                mActionBarContain.setVisibility(View.VISIBLE);
            }
        }
    }


    public void setActionBarBgColor(int color) {
        if (mActionBarBg == null) return;

        if (color == -1) {
            ChengXiApplication application = (ChengXiApplication) mContext.getApplicationContext();
            mActionBarBg.setBackgroundColor(application.getActionBarBgColor());

        } else {
            mActionBarBg.setBackgroundColor(color);
        }
    }

    @Override
    public Context getContext() {
        return mContext;
    }


}
