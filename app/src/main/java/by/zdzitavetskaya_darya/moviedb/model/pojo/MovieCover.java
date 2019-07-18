package by.zdzitavetskaya_darya.moviedb.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCover {
    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<SubMovie> subMovies;

    public List<SubMovie> getSubMovies() {
        return subMovies;
    }

    public void setSubMovies(final List<SubMovie> subMovies) {
        this.subMovies = subMovies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(final int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(final int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }
}
