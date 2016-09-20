package me.bugsrain.test1.ui.main;

import android.os.Bundle;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import me.bugsrain.test1.R;
import me.bugsrain.test1.entity.Doctor;
import me.bugsrain.test1.frame.base.BaseFrameActivity;

public class MainActivity extends BaseFrameActivity<MainPresenter, MainModel> implements MainContract.View {

    PtrClassicFrameLayout ptrClassicFrameLayout;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("标题");
        ptrClassicFrameLayout = findViewByIdToView(R.id.ptr);
    }

    @Override
    public void initListener() {
        super.initListener();

        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.getDoctorList(1);
            }
        });
    }

    @Override
    public void initLoad() {
        super.initLoad();
        ptrClassicFrameLayout.post(() ->
                ptrClassicFrameLayout.autoRefresh()
        );
    }

    @Override
    public void onRequestStart() {
    }

    @Override
    public void onRequestEnd() {
        ptrClassicFrameLayout.refreshComplete();
    }

    @Override
    public void onGetDoctorSuccess(List<Doctor> doctorList) {

    }
}
