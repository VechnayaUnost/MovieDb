package by.zdzitavetskaya_darya.moviedb.model.pojo;

import androidx.room.Entity;

@Entity
public class SubMovie extends Movie {

    private boolean isTopRated;

    private boolean isUpcoming;

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
}