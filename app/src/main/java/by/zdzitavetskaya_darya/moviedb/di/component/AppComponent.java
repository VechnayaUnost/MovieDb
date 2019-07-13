package by.zdzitavetskaya_darya.moviedb.di.component;

import javax.inject.Singleton;

import by.zdzitavetskaya_darya.moviedb.di.module.RetrofitModule;
import by.zdzitavetskaya_darya.moviedb.di.module.RoomModule;
import dagger.Component;

@Singleton
@Component(modules = {RetrofitModule.class, RoomModule.class})
public interface AppComponent {

}
