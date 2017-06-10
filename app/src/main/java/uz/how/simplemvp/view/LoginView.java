package uz.how.simplemvp.view;

/**
 * Created by mirjalol on 6/9/17.
 */

public interface LoginView {

    void validationError();

    void authError();

    void authSuccess();

    void showProgress();

    void hideProgress();

    void connectionError();

}
