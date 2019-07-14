package by.zdzitavetskaya_darya.moviedb.presentation;

import io.reactivex.disposables.CompositeDisposable;
import moxy.MvpPresenter;
import moxy.MvpView;

public abstract class BasePresenter <V extends MvpView> extends MvpPresenter<V> {

    final protected CompositeDisposable compositeDisposable;

    public BasePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
