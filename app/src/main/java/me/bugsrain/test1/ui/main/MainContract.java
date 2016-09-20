package me.bugsrain.test1.ui.main;

import java.util.List;

import chengxinet.chengxilibs.entity.Entity;
import me.bugsrain.test1.entity.Doctor;
import me.bugsrain.test1.frame.BaseModel;
import me.bugsrain.test1.frame.BasePresenter;
import me.bugsrain.test1.frame.BaseView;
import rx.Observable;

/**
 * Created by quan on 16/9/19.
 */

public interface MainContract {
    interface Model extends BaseModel {
        Observable<Entity<List<Doctor>>> getDoctorList(int page);
    }

    interface View extends BaseView {
        void onGetDoctorSuccess(List<Doctor> doctorList);
    }

    abstract class Presenter extends BasePresenter<Model, View>{
        abstract void getDoctorList(int page);
    }
}
