package uz.how.simplemvp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uz.how.simplemvp.model.domains.Repo;
import uz.how.simplemvp.model.domains.User;

/**
 * Created by mirjalol on 6/10/17.
 */

public interface GithubService {

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String username);

    @GET("users/{user}/repos")
    Call<List<Repo>> getRepos(@Path("user") String username);

}
