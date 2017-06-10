package uz.how.simplemvp.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import uz.how.simplemvp.R;
import uz.how.simplemvp.model.Constants;
import uz.how.simplemvp.presenter.impl.LoginPresenterImpl;
import uz.how.simplemvp.view.LoginView;

/**
 * Created by mirjalol on 6/9/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    LoginPresenterImpl loginPresenter;
    @Inject
    SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        if (prefs.contains(Constants.LOGIN)) {
            ProfileActivity.start(this);
            return;
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void validationError() {
        if (username.getText().toString().trim().isEmpty()) {
            username.setError("Fill username");
        }
        if (password.getText().toString().trim().isEmpty()) {
            password.setError("Fill password");
        }
    }

    @Override
    public void authError() {
        Toast.makeText(this, R.string.auth_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void authSuccess() {
        ProfileActivity.start(this);
    }

    @Override
    public void showProgress() {
        username.setEnabled(false);
        password.setEnabled(false);
        loginButton.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        username.setEnabled(true);
        password.setEnabled(true);
        loginButton.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void connectionError() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.loginButton)
    void onLogin() {
        loginPresenter.provideLogin(
                username.getText().toString().trim(),
                password.getText().toString().trim()
        );
    }

}
