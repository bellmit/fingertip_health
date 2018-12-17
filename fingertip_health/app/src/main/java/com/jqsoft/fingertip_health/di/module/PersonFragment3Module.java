package com.jqsoft.fingertip_health.di.module;

import com.jqsoft.fingertip_health.di.contract.HighBloodFragmentContract;
import com.jqsoft.fingertip_health.di.contract.PersonFragment3Contract;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/5/21.
 */

@Module
public class PersonFragment3Module {

    private PersonFragment3Contract.View view;

    public PersonFragment3Module(PersonFragment3Contract.View view){
        this.view = view;
    }

    @FragmentScope
    @Provides
    public PersonFragment3Contract.View providerView(){
        return view;
    }

}
