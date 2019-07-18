package by.zdzitavetskaya_darya.moviedb.api;

import by.zdzitavetskaya_darya.moviedb.model.pojo.Movie;
import by.zdzitavetskaya_darya.moviedb.model.pojo.MovieCover;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/top_rated")
    Single<MovieCover> getTopRated(@Query("page") int page);

    @GET("movie/upcoming")
    Single<MovieCover> getUpcoming(@Query("page") int page);

    @GET("search/movie")
    Single<MovieCover> searchMovies(@Query("page") int page, @Query("query") String query);

    @GET("movie/{movie_id}")
    Single<Movie> getMovieById(@Path("movie_id") int id);
}
