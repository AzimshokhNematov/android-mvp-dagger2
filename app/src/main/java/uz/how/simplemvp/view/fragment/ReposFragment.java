package uz.how.simplemvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import uz.how.simplemvp.R;
import uz.how.simplemvp.model.domains.Repo;
import uz.how.simplemvp.presenter.impl.ReposPresenterImpl;
import uz.how.simplemvp.view.ReposView;

/**
 * Created by mirjalol on 6/10/17.
 */

public class ReposFragment extends Fragment implements ReposView {

    public static ReposFragment newInstance() {
        return new ReposFragment();
    }

    @Inject ReposPresenterImpl reposPresenter;
    @Inject LayoutInflater layoutInflater;

    @BindView(R.id.placeHolderText) TextView placeHolderText;
    @BindView(R.id.recylerView) RecyclerView recyclerView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_repos, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reposPresenter.loadRepos();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setRepoList(List<Repo> repoList) {
        if (repoList.size()>0) {
            recyclerView.setAdapter(new ReposAdapter(repoList));
        } else {
            placeHolderText.setVisibility(View.VISIBLE);
        }
    }

    class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

        private List<Repo> repoList;

        ReposAdapter(List<Repo> repoList) {
            this.repoList = repoList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = layoutInflater.inflate(R.layout.item_repo, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Repo repo = repoList.get(i);
            viewHolder.repoName.setText(repo.getName());
            viewHolder.repoFullName.setText(repo.getFullName());
        }

        @Override
        public int getItemCount() {
            return repoList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.repoName) TextView repoName;
            @BindView(R.id.repoFullName) TextView repoFullName;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

    }

}
