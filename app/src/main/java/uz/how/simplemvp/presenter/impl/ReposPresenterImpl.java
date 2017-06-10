package uz.how.simplemvp.presenter.impl;

import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.how.simplemvp.model.Constants;
import uz.how.simplemvp.model.GithubService;
import uz.how.simplemvp.model.domains.Repo;
import uz.how.simplemvp.presenter.ReposPresenter;
import uz.how.simplemvp.view.ReposView;
import uz.how.simplemvp.view.fragment.ReposFragment;

/**
 * Created by mirjalol on 6/10/17.
 */

public class ReposPresenterImpl implements ReposPresenter, Callback<List<Repo>> {

    private ReposView reposView;
    private GithubService githubService;
    private SharedPreferences prefs;

    @Inject
    public ReposPresenterImpl(ReposFragment reposView, GithubService githubService, SharedPreferences prefs) {
        this.reposView = reposView;
        this.githubService = githubService;
        this.prefs = prefs;
    }

    @Override
    public void loadRepos() {
        reposView.showProgress();
        githubService.getRepos(prefs.getString(Constants.LOGIN, "")).enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        reposView.hideProgress();
        if (response.body()!=null) {
            reposView.setRepoList(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<Repo>> call, Throwable t) {
        reposView.hideProgress();
    }
}
