package uz.how.simplemvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import uz.how.simplemvp.R;
import uz.how.simplemvp.dagger.module.GlideApp;
import uz.how.simplemvp.model.domains.User;
import uz.how.simplemvp.presenter.impl.ProfilePresenterImpl;
import uz.how.simplemvp.view.ProfileView;
import uz.how.simplemvp.view.fragment.ReposFragment;

/**
 * Created by mirjalol on 6/9/17.
 */

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    public static void start(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.profileImage) ImageView profileImage;
    @BindView(R.id.profileName) TextView profileName;
    @BindView(R.id.tryAgain) Button tryAgain;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Inject
    ProfilePresenterImpl profilePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        profilePresenter.loadProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout: {
                profilePresenter.logout();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
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
    public void setProfileData(User user) {
        profileName.setText(user.getName());

        GlideApp.with(this)
                .load(user.getAvatarUrl())
                .centerCrop()
                .into(profileImage);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.reposContainer, ReposFragment.newInstance())
                .commit();
    }

    @Override
    public void connectionError() {
        tryAgain.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tryAgain)
    void onTryAgain() {
        profilePresenter.loadProfile();
    }

}
