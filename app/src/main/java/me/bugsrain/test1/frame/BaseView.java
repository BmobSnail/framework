package me.bugsrain.test1.frame;

/**
 * Created by baixiaokang on 16/4/22.
 */
public interface  BaseView {
    void onRequestStart();
    void onRequestError(String msg);
    void onRequestEnd();
    void onInternetError();

}
