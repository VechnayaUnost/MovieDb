package by.zdzitavetskaya_darya.moviedb;

import android.app.Application;

import com.facebook.stetho.Stetho;

import by.zdzitavetskaya_darya.moviedb.di.component.AppComponent;
import by.zdzitavetskaya_darya.moviedb.di.component.DaggerAppComponent;
import by.zdzitavetskaya_darya.moviedb.di.module.RoomModule;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        initStetho();
        appComponent = buildComponent();
    }

    public AppComponent buildComponent() {
        return DaggerAppComponent
                    .builder()
                    .roomModule(new RoomModule(this))
                    .build();
    }

    public void initStetho() {
        final Stetho.InitializerBuilder initializerBuilder = Stetho.newInitializerBuilder(this);
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this));
        Stetho.initialize(initializerBuilder.build());
    }
}
