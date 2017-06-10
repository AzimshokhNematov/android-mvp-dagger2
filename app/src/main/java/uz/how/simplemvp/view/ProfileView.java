package uz.how.simplemvp.view;

import uz.how.simplemvp.model.domains.User;

/**
 * Created by mirjalol on 6/10/17.
 */

public interface ProfileView {

    void showProgress();

    void hideProgress();

    void setProfileData(User user);

    void connectionError();

    void goToLogin();

}
