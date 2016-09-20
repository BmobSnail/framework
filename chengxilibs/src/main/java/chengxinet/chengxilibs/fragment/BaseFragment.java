package chengxinet.chengxilibs.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import chengxinet.chengxilibs.activity.BaseActivity;
import chengxinet.chengxilibs.global.BaseImplCompat;
import chengxinet.chengxilibs.helper.BaseImplCompatHelper;
import chengxinet.chengxilibs.utils.MyLog;


public abstract class BaseFragment extends Fragment implements OnClickListener,
		OnItemClickListener , BaseImplCompat, OnDismissListener {

	protected FragmentCallBack callBack;

	/** 界面视图 */
	protected View mContentView;

	protected ViewGroup mContainer;

	public String TAG;

	private android.os.Bundle savedInstanceState;

	private BaseImplCompatHelper mHelper;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		MyLog.i("LifeCycle", getClass().getSimpleName() + " is onAttach()");
		if(context instanceof FragmentCallBack){
			this.callBack = (FragmentCallBack)context;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new BaseImplCompatHelper(this);
		onSetView(savedInstanceState);
		TAG = getClass().getSimpleName();
		MyLog.i("LifeCycle", getClass().getSimpleName() + " is onCreate()");
		this.savedInstanceState = savedInstanceState;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	public abstract void onSetView(Bundle savedInstanceState);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		MyLog.i("LifeCycle", getClass().getSimpleName() + " is onCreateView()");
		mContainer = container;
		initData();
		initView();
		initListener();
		initLoad();
		return mContentView;

	}

	@Override
	public void onStart() {
		MyLog.i("LifeCycle", getClass().getSimpleName() + " is onStart()");
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
		MyLog.i("LifeCycle", getClass().getSimpleName() + " is onStop()");
	}

	@Override
	public void onResume() {
		super.onResume();


	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onDestroy() {
		MyLog.i("LifeCycle", getClass().getSimpleName() + " is onDestroy()");
		mContainer = null;
		super.onDestroy();
	}

	@Override
	public void openActivity(Class<? extends BaseActivity> toActivity) {
		mHelper.openActivity(toActivity);
	}

	@Override
	public void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
		mHelper.openActivity(toActivity, parameter);
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
	public View getActionBarBg() {
		return mHelper.getActionBarBg();
	}

	@Override
	public void setActionBarBgColor(int color) {
		mHelper.setActionBarBgColor(color);
	}

	/**
	 * 设置界面视图
	 * 
	 * @param id
	 *            视图的资源id
	 * */
	public void setmContentView(int id) {
		this.mContentView = getActivity().getLayoutInflater().inflate(id, mContainer, false);
	}

	@Override
	public  <T extends View> T findViewByIdToView(int id) {
		return mHelper.findViewByIdToView(id);
	}

	@CallSuper
	@Override
	public void initData() {

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

	public void onDismiss(DialogInterface dialog) {

	}

	@CallSuper
	@Override
	public void initLoad() {
		
	}
	
	public void finish(){
		getActivity().finish();
	}

	public View getmContentView() {
		return mContentView;
	}

	public void setContentView(View contentView) {
		this.mContentView = contentView;
	}

	public interface FragmentCallBack{
		void callBack(Object... arg);
	}

	protected FragmentCallBack getCallBack() {
		return callBack;
	}


	public LayoutInflater getLayoutInflater() {
		return super.getLayoutInflater(savedInstanceState);
	}


	public void onClick(View v) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	}

	@Override
	public void setTitle(String title) {
		mHelper.setTitle(title);
	}

	@Override
	public void setFunctionView(View v, String content, int picId) {
		mHelper.setFunctionView(v, content, picId);
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
	public Context getContext() {
		return mHelper.getContext();
	}
}
