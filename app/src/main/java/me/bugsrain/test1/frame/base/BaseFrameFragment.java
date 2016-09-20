package me.bugsrain.test1.frame.base;

import android.os.Bundle;

import chengxinet.chengxilibs.fragment.BaseFragment;
import chengxinet.chengxilibs.utils.TUtil;
import me.bugsrain.test1.frame.BaseModel;
import me.bugsrain.test1.frame.BasePresenter;
import me.bugsrain.test1.frame.BaseView;

/**
 * Created by quan on 16/9/20.
 */

public abstract  class BaseFrameFragment<P extends BasePresenter, M extends BaseModel> extends BaseFragment implements BaseView {

    public P mPresenter;

    public M mModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (this instanceof BaseView) {
            mPresenter.setVM(this, mModel);
        }

    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onInternetError() {
        showShortToast("网络异常");
    }

    @Override
    public void onRequestError(String msg) {
        showShortToast(msg);
    }

}
