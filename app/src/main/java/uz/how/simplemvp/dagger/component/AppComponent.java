package uz.how.simplemvp.dagger.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import uz.how.simplemvp.MvpApplication;
import uz.how.simplemvp.dagger.module.AppModule;
import uz.how.simplemvp.dagger.module.BuildersModule;

/**
 * Created by mirjalol on 6/10/17.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, BuildersModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MvpApplication application);
        AppComponent build();
    }
    void inject(MvpApplication application);

}
