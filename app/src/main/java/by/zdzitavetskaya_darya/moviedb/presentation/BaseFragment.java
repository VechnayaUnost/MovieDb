package by.zdzitavetskaya_darya.moviedb.presentation;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;

public abstract class BaseFragment<P extends BasePresenter> extends MvpAppCompatFragment {

    @InjectPresenter
    P presenter;

    private Unbinder unbinder;

    public abstract RecyclerView getRecycler();

    public abstract int getLayoutFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutFragment(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter != null) {
            presenter.onViewAttached();
        }
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        getRecycler().setLayoutManager(layoutManager);

        final MoviesAdapter adapter = new MoviesAdapter(new ArrayList<>());
        getRecycler().setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.onViewDetached();
        }
        unbinder.unbind();
    }
}
