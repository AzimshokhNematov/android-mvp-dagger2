package uz.how.simplemvp.view;

import java.util.List;

import uz.how.simplemvp.model.domains.Repo;

/**
 * Created by mirjalol on 6/10/17.
 */

public interface ReposView {

    void showProgress();
    void hideProgress();
    void setRepoList(List<Repo> repoList);

}
