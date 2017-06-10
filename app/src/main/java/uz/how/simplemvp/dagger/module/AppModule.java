package uz.how.simplemvp.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uz.how.simplemvp.MvpApplication;
import uz.how.simplemvp.dagger.subcomponent.LoginSubComponent;
import uz.how.simplemvp.dagger.subcomponent.ProfileSubComponent;
import uz.how.simplemvp.dagger.subcomponent.ReposSubComponent;
import uz.how.simplemvp.model.GithubService;

/**
 * Created by mirjalol on 6/10/17.
 */
@Module(subcomponents = {LoginSubComponent.class, ProfileSubComponent.class, ReposSubComponent.class})
public class AppModule {

    @Provides Context provideContext(MvpApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    GithubService provideApiService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }



}
