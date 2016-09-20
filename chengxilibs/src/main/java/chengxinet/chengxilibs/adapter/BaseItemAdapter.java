package chengxinet.chengxilibs.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import chengxinet.chengxilibs.global.BaseImplCompat;

public class BaseItemAdapter extends BaseAdapter{

	private Context mContext;
	private LayoutInflater inflate;

	public BaseItemAdapter(BaseImplCompat baseImplCompat){
		this(baseImplCompat.getContext());
	}

	public BaseItemAdapter(Context context){
		mContext = context;
		inflate = LayoutInflater.from(mContext);
	}
	
	public int getCount() {
		return 0;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	protected void openActivity(Class<?> toActivity) {
		openActivity(toActivity, null);
	}

	protected void openActivity(Class<?> toActivity, Bundle parameter) {
		Intent intent = new Intent(mContext, toActivity);
		if (parameter != null) {
			intent.putExtras(parameter);
		}
		mContext.startActivity(intent);

	}

	protected void showShortToast(String pMsg) {
		Toast.makeText(mContext, pMsg, Toast.LENGTH_SHORT).show();
	}

	public Context getContext() {
		return mContext;
	}

	public View getItemLayout(int id, ViewGroup group) {
		return inflate.inflate(id, group, false);
	}

}
