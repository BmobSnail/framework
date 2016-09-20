package chengxinet.chengxilibs.global;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import chengxinet.chengxilibs.activity.BaseActivity;

public interface BaseImplCompat {

	/** 初始化数据方法 */
	void initData();

	/** 初始化UI控件方法 */
	void initView();

	/** 初始化事件监听方法 */
	void initListener();

	void initLoad();

	void openActivity(Class<? extends BaseActivity> toActivity);

	void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter);

	void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter, int code);

	void showLongToast(String pMsg);

	void showShortToast(String pMsg);

	void hideKeyboard(View v);

	<T extends View> T findViewByIdToView(int id);

	void setTitle(String title);

	void setFunctionView(View v, String content, int picId);

	TextView getCenterTxt();

	View getLeft();

	View getRight();

	View getActionBarBg();

	void hideActionBar(boolean b);

	void setActionBarBgColor(int color);

	Context getContext();
}
