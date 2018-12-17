//package com.jqsoft.grassroots_civil_administration_platform.di.module;
//
//import com.jqsoft.fingertip_health.di.contract.AppointmentRegistrationActivityContract;
//import com.jqsoft.fingertip_health.di_app.ActivityScope;
//
//import dagger.Module;
//import dagger.Provides;
//
///**
// * Created by Administrator on 2017/5/21.
// */
//
//@Module
//public class AppointmentRegistrationActivityModule {
//
//    private AppointmentRegistrationActivityContract.View view;
//
//    public AppointmentRegistrationActivityModule(AppointmentRegistrationActivityContract.View view){
//        this.view = view;
//    }
//
//    @ActivityScope
//    @Provides
//    public AppointmentRegistrationActivityContract.View providerView(){
//        return view;
//    }
//
//}
