package me.bugsrain.test1.frame.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by baixiaokang on 16/5/6.
 */
public class RxSchedulers {
//    public static <T> Observable.Transformer<T, T> handleDialog(final Context context) {
//        return tObservable -> {
//            tObservable.subscribe(new ProgressSubscriber<>(context));
//            return tObservable;
//        };
//
//    }

    public static <T> Observable.Transformer<T, T> io_main() {

        return tObservable -> tObservable
                //生产线程
                .subscribeOn(Schedulers.io())
                //消费线程
                .observeOn(AndroidSchedulers.mainThread());

    }
}
