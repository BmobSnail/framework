package chengxinet.chengxilibs.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import chengxinet.chengxilibs.R;


public class BaseDialog extends Dialog{

	private Context mContext;
	
	public BaseDialog(Context context, int theme) {
		super(context, theme);
		mContext = context;
	}

	public BaseDialog(Context context) {
		this(context, R.style.myDialog);
	}
	
	public LayoutParams getParams(){
		return new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
	}
	
	public View getView(int layoutId){
		if (mContext != null) {
			return LayoutInflater.from(mContext).inflate(layoutId, null);
		}
		return null;
	}
	
	
//	public View getViewById(View mContentView, int id){
//		if (mContentView!=null) {
//			return mContentView.findViewById(id);
//		}
//		return null;
//	}
	
	public void show(boolean b) {
		if (!b) {
			setCancelable(b);
		}
		super.show();
	}
}
