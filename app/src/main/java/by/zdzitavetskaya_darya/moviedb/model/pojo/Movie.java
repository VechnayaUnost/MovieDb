package by.zdzitavetskaya_darya.moviedb.model.pojo;

import com.google.gson.annotations.SerializedName;

//@Entity
public class Movie {

    @SerializedName("vote_count")
    private int voteCount;

    //@PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    private boolean isTopRated;

    private boolean isUpcoming;

    private boolean isFavourite;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(final int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public boolean getVideo() {
        return video;
    }

    public void setVideo(final boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(final float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(final float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(final String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(final String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(final String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(final String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(final boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(final String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isTopRated() {
        return isTopRated;
    }

    public void setTopRated(final boolean topRated) {
        isTopRated = topRated;
    }

    public boolean isUpcoming() {
        return isUpcoming;
    }

    public void setUpcoming(final boolean upcoming) {
        isUpcoming = upcoming;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(final boolean favourite) {
        isFavourite = favourite;
    }
}