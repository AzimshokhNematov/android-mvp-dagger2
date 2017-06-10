package uz.how.simplemvp.dagger.module;

import dagger.Binds;
import dagger.Module;
import uz.how.simplemvp.view.fragment.ReposFragment;

/**
 * Created by mirjalol on 6/10/17.
 */
@Module
public abstract class ReposModule {

    @Binds
    abstract ReposFragment provideReposView(ReposFragment reposFragment);

}
