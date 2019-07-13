package by.zdzitavetskaya_darya.moviedb.di.component;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.di.module.DatabaseModule;
import by.zdzitavetskaya_darya.moviedb.di.module.NetworkModule;
import by.zdzitavetskaya_darya.moviedb.di.module.RetrofitModule;
import by.zdzitavetskaya_darya.moviedb.di.module.RoomModule;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class, RoomModule.class, DatabaseModule.class, NetworkModule.class})
public interface AppComponent {

}
