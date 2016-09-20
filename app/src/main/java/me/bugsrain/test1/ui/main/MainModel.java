package me.bugsrain.test1.ui.main;

import java.util.List;

import chengxinet.chengxilibs.entity.Entity;
import me.bugsrain.test1.api.Api;
import me.bugsrain.test1.entity.Doctor;
import me.bugsrain.test1.frame.rx.RxSchedulers;
import rx.Observable;

/**
 * Created by quan on 16/9/19.
 */

public class MainModel implements MainContract.Model {

    @Override
    public Observable<Entity<List<Doctor>>> getDoctorList(int page) {
        return Api.getInstance().getService().getDoctor(page).compose(RxSchedulers.io_main());
    }
}
