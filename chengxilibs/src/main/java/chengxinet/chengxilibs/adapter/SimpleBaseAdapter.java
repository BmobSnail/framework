package chengxinet.chengxilibs.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import chengxinet.chengxilibs.utils.SharedPreferencesHelper;
import chengxinet.chengxilibs.widget.MySimpleDraweeView;

/**
 * Created by Jun on 2015/5/18.
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {

    public Context mContext;
    public LayoutInflater mInflater;

    public List<T> mData;
    public int layoutId;

    public SimpleBaseAdapter(Context context, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public SimpleBaseAdapter(Context context, int layoutId, List<T> data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.mData = data;

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        if (mData != null)
            return mData.get(position);
        else
            return null;
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);

        if (getItem(position) != null)
            getItemView(holder, position, getItem(position));

        return holder.getConvertView();
    }

    public abstract void getItemView(ViewHolder holder, int position, T t);

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

    protected void showLongToast(String pMsg) {
        Toast.makeText(mContext, pMsg, Toast.LENGTH_LONG).show();
    }

    protected void showShortToast(String pMsg) {
        Toast.makeText(mContext, pMsg, Toast.LENGTH_SHORT).show();
    }


    public void addAll(List<T> elem) {
        mData.addAll(elem);
        notifyDataSetChanged();
    }

    public void add(T elem) {
        mData.add(elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mData.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        if (mData.size() != 0)
            mData.clear();

        if (elem != null) {
            mData.addAll(elem);
        }
        notifyDataSetChanged();
    }


    @Override
    public void notifyDataSetChanged() {

        super.notifyDataSetChanged();
    }

    public static class ViewHolder {
        private SparseArray<View> mViews;
        private int position;
        private View mConvertView;
        private Context mContext;

        private SharedPreferencesHelper mSPHelper;

        public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
            this.mContext = context;
            this.position = position;
            this.mViews = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            mConvertView.setTag(this);

            mSPHelper = SharedPreferencesHelper.getInstance(context);
        }

        public static ViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId, int position) {
            if (convertView == null) {
                return new ViewHolder(context, parent, layoutId, position);
            } else {
                ViewHolder holder = (ViewHolder) convertView.getTag();
                holder.position = position;
                return holder;
            }
        }

        public View getConvertView() {
            return mConvertView;
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);

            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public int getPosition() {
            return position;
        }

        public ViewHolder setText(int viewId, String text) {
            TextView tv = getView(viewId);
            tv.setText(text);
            return this;
        }

        public ViewHolder setProess(int viewId, int progress) {
            ProgressBar pb = getView(viewId);
            pb.setProgress(progress);
            return this;
        }

        public ViewHolder setImageResource(int viewId, int resourceId) {
            ImageView imageView = getView(viewId);
            imageView.setImageResource(resourceId);
            return this;
        }


        public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
            ImageView imageView = getView(viewId);
            imageView.setImageBitmap(bitmap);
            return this;
        }

        /**
         * 使用 Fresco 缓存库
         *
         * @param viewId 图片Id
         * @param url    图片地址
         * @return
         */
        public ViewHolder setImageURL(int viewId, String url) {
            return setImageURL(viewId, url, null);
        }

        public ViewHolder setImageURL(int viewId, String url,  String quality) {
            if(!TextUtils.isEmpty(url)) {
                MySimpleDraweeView imageView = getView(viewId);
                imageView.setImageUrl(url, quality);
            }
            return this;
        }

        public ViewHolder setIconImageURL(int viewId, String url) {
            ImageView imageView = getView(viewId);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            ImageLoader.getInstance().displayImage(url, imageView, OneYuanShop.getDisplayImageOption(0));
            return this;
        }

        public Context getContext() {
            return mContext;
        }
    }

}
