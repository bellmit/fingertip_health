package com.jqsoft.fingertip_health.di.component;

import com.jqsoft.fingertip_health.di.module.HarbelFragmentModule;
import com.jqsoft.fingertip_health.di.module.RecipeFragmentModule;
import com.jqsoft.fingertip_health.di.ui.fragment.HarbelFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.RecipeFragment;
import com.jqsoft.fingertip_health.di_app.FragmentScope;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/5/21.
 */

@FragmentScope
@Subcomponent(modules = RecipeFragmentModule.class)
public interface RecipeFragmentComponent {
    void inject(RecipeFragment recipeFragment);
}
