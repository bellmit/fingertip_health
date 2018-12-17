package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.TownLevelMedicalInstitutionDirectoryFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.TownLevelMedicalInstitutionDirectoryFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = TownLevelMedicalInstitutionDirectoryFragmentModule.class)
public interface TownLevelMedicalInstitutionDirectoryFragmentComponent {
    void inject(TownLevelMedicalInstitutionDirectoryFragment townLevelMedicalInstitutionDirectoryFragment);
}
