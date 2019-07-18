package by.zdzitavetskaya_darya.moviedb.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import by.zdzitavetskaya_darya.moviedb.R;
import by.zdzitavetskaya_darya.moviedb.adapters.MoviesAdapter;
import by.zdzitavetskaya_darya.moviedb.constants.Constants;
import by.zdzitavetskaya_darya.moviedb.presentation.detailPresentation.DetailActivity;
import moxy.MvpAppCompatFragment;

public abstract class BaseFragment extends MvpAppCompatFragment implements MoviesAdapter.Listener {

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    protected MoviesAdapter adapter;
    protected boolean isFetchingMovies;
    protected int currentPage = 1;

    protected abstract void getMoreMovies(int currentPage);

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                final int totalItemCount = layoutManager.getItemCount();
                final int visibleItemCount = layoutManager.getChildCount();
                final int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    if (!isFetchingMovies) {
                        getMoreMovies(currentPage + 1);
                    }
                }
            }
        });
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
