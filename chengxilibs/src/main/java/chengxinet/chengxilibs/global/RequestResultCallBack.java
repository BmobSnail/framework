package chengxinet.chengxilibs.global;

/**
 * Created by quan on 16/6/28.
 */
public interface RequestResultCallBack {
    void onEmpty();
    void onNetworkError();
    void hideEmptyView();
    void hideErrorView();
    void setFirstRequestServiceSuccess(boolean flag);
}
