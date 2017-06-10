package uz.how.simplemvp.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import uz.how.simplemvp.dagger.subcomponent.LoginSubComponent;
import uz.how.simplemvp.dagger.subcomponent.ProfileSubComponent;
import uz.how.simplemvp.dagger.subcomponent.ReposSubComponent;
import uz.how.simplemvp.view.activity.LoginActivity;
import uz.how.simplemvp.view.activity.ProfileActivity;
import uz.how.simplemvp.view.fragment.ReposFragment;

/**
 * Created by mirjalol on 6/10/17.
 */
@Module
public abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindLoginActivityInjectorFactory(LoginSubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(ProfileActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindProfileActivityInjectorFactory(ProfileSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ReposFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindReposFragmentInjectorFactory(ReposSubComponent.Builder builder);

}
