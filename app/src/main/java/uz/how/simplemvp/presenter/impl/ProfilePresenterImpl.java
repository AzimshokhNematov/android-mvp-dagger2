package uz.how.simplemvp.presenter.impl;

import android.content.SharedPreferences;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.how.simplemvp.model.Constants;
import uz.how.simplemvp.model.GithubService;
import uz.how.simplemvp.model.domains.User;
import uz.how.simplemvp.presenter.ProfilePresenter;
import uz.how.simplemvp.view.ProfileView;
import uz.how.simplemvp.view.activity.ProfileActivity;

/**
 * Created by mirjalol on 6/10/17.
 */

public class ProfilePresenterImpl implements ProfilePresenter, Callback<User> {

    private ProfileView profileView;
    private GithubService githubService;
    private SharedPreferences prefs;

    @Inject
    public ProfilePresenterImpl(ProfileActivity profileView, GithubService githubService, SharedPreferences prefs) {
        this.profileView = profileView;
        this.githubService = githubService;
        this.prefs = prefs;
    }

    @Override
    public void loadProfile() {
        profileView.showProgress();
        githubService.getUser(prefs.getString(Constants.LOGIN, null)).enqueue(this);
    }

    @Override
    public void logout() {
        prefs.edit().remove(Constants.LOGIN).apply();
        profileView.goToLogin();
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        profileView.hideProgress();
        User user = response.body();
        if (user!=null) {
            profileView.setProfileData(user);
        } else {
            profileView.connectionError();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        profileView.hideProgress();
        profileView.connectionError();
    }
}
