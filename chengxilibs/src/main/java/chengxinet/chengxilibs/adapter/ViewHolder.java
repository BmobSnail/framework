package chengxinet.chengxilibs.adapter;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder {
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
        SparseArray<View> sa = (SparseArray<View>) view.getTag();
        if (sa == null) {
            sa = new SparseArray<View>();
            view.setTag(sa);
        }  
        View childView = sa.get(id);
        if (childView == null) {  
            childView = view.findViewById(id);
            sa.put(id, childView);
        }  
        return (T) childView;  
    }


}
