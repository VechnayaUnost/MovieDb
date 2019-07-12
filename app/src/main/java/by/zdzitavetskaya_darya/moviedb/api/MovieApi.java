package by.zdzitavetskaya_darya.moviedb.api;

import by.zdzitavetskaya_darya.moviedb.model.pojo.MovieCover;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("/movie/top_rated")
    Single<MovieCover> getTopRated(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Single<MovieCover> getUpcoming(@Query("api_key") String apiKey);

    @GET("/search/movie")
    Single<MovieCover> searchMovies(@Query("query") String query, @Query("api_key") String apiKey);
}