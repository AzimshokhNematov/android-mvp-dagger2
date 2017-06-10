package uz.how.simplemvp.presenter.impl;

import android.content.SharedPreferences;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.how.simplemvp.model.Constants;
import uz.how.simplemvp.model.GithubService;
import uz.how.simplemvp.model.domains.User;
import uz.how.simplemvp.presenter.LoginPresenter;
import uz.how.simplemvp.view.LoginView;
import uz.how.simplemvp.view.activity.LoginActivity;

/**
 * Created by mirjalol on 6/9/17.
 */

public class LoginPresenterImpl implements LoginPresenter, Callback<User> {

    private LoginView loginView;
    private GithubService githubService;
    private SharedPreferences prefs;

    @Inject
    public LoginPresenterImpl(LoginActivity loginView, GithubService githubService, SharedPreferences prefs) {
        this.loginView = loginView;
        this.githubService = githubService;
        this.prefs = prefs;
    }

    @Override
    public void provideLogin(final String username, final String password) {
        if (loginView==null) return;
        if (username.isEmpty() || password.isEmpty()) {
            loginView.validationError();
        } else {
            loginView.showProgress();
            githubService.getUser(username).enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        loginView.hideProgress();
        User user = response.body();
        if (user!=null && user.getId()!=null) {
            prefs.edit().putString(Constants.LOGIN, user.getLogin()).apply();
            loginView.authSuccess();
        } else {
            loginView.authError();
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        loginView.hideProgress();
        loginView.connectionError();
    }
}
