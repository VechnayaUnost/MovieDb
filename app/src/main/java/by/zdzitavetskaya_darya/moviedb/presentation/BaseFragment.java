package by.zdzitavetskaya_darya.moviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import by.zdzitavetskaya_darya.moviedb.presentation.detailPresentation.DetailActivity;
import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.adapters.MoviesAdapter;
import by.zdzitavetskaya_darya.moviedb.constants.Constants;
import moxy.MvpAppCompatActivity;
import moxy.MvpAppCompatFragment;

public abstract class BaseFragment extends MvpAppCompatFragment implements MoviesAdapter.Listener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    protected MoviesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        unbinder = ButterKnife.bind(this, view);

        ((MvpAppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(final int id) {
        final Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.ARG_ID, id);
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }
}
