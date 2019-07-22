package by.zdzitavetskaya_darya.moviedb.di.component;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.di.module.DatabaseModule;
import by.zdzitavetskaya_darya.moviedb.di.module.NetworkModule;
import by.zdzitavetskaya_darya.moviedb.di.module.RetrofitModule;
import by.zdzitavetskaya_darya.moviedb.di.module.RoomModule;
import by.zdzitavetskaya_darya.moviedb.model.DatabaseModel;
import by.zdzitavetskaya_darya.moviedb.model.NetworkModel;
import by.zdzitavetskaya_darya.moviedb.presentation.detailPresentation.DetailPresenter;
import by.zdzitavetskaya_darya.moviedb.presentation.favouritePresentation.FavouritePresenter;
import by.zdzitavetskaya_darya.moviedb.presentation.searchPresentation.SearchPresenter;
import by.zdzitavetskaya_darya.moviedb.presentation.topRatedPresentation.TopRatedPresenter;
import by.zdzitavetskaya_darya.moviedb.presentation.upcomingPresentation.UpcomingPresenter;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class, RoomModule.class, DatabaseModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(DatabaseModel databaseModel);
    void inject(NetworkModel networkModel);

    void inject(TopRatedPresenter topRatedPresenter);
    void inject(UpcomingPresenter upcomingPresenter);
    void inject(SearchPresenter searchPresenter);
    void inject(DetailPresenter detailPresenter);
    void inject(FavouritePresenter favouritePresenter);
}
